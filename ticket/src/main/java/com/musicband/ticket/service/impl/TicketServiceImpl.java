package com.musicband.ticket.service.impl;

import com.musicband.ticket.dto.*;
import com.musicband.ticket.entity.Ticket;
import com.musicband.ticket.entity.TicketOrder;
import com.musicband.ticket.exceptions.ResourceNotFoundException;
import com.musicband.ticket.exceptions.TicketAlreadyExistsException;
import com.musicband.ticket.mapper.TicketMapper;
import com.musicband.ticket.mapper.TicketOrderMapper;
import com.musicband.ticket.repository.TicketOrderRepository;
import com.musicband.ticket.repository.TicketRepository;
import com.musicband.ticket.service.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
    private static final int DEFAULT_TICKET_COUNT = 10;
    private final TicketRepository ticketRepository;
    private final TicketOrderRepository ticketOrderRepository;
    private final StreamBridge streamBridge;

    @Override
    public void addTicket(TicketDto ticketDto) {
        if(ticketRepository.findTicketByPlaceAndTourId(ticketDto.getPlace(), ticketDto.getTourId()).isPresent()){
            throw new TicketAlreadyExistsException("Ticket already exists");
        }
        Ticket savedTicket = ticketRepository.save(TicketMapper.ticketDtoToTicket(ticketDto,new Ticket()));
        newTicketsFotTour(savedTicket);
        log.info("Ticked saved : {}", savedTicket);
    }

    private void newTicketsFotTour(Ticket ticket) {
        TicketMsgDto ticketMsgDto = new TicketMsgDto(
                ticket.getPrice(),
                ticket.getPlace(),
                ticket.getTourId()
        );
        log.info("New Ticket events: {}",ticketMsgDto);
        boolean result = streamBridge.send("orderStatusPayment-out-0", ticketMsgDto);
        log.info("Result of adding: {}",result);
    }

    @Override
    public void addTicketsForTour(Long tourId) {
        List<TicketDto> createdTickets = IntStream.range(0, DEFAULT_TICKET_COUNT)
                        .mapToObj(i -> TicketDto.builder()
                                .price(99.99)
                                .place("A"+i)
                                .tourId(tourId)
                                .build()
                        ).toList();

        createdTickets.forEach(this::addTicket);
    }

    @Override
    public List<TicketDto> getTickets() {
        return ticketRepository.findAll().stream().map(ticket -> TicketMapper.ticketToTicketDto(ticket, new TicketDto())).toList();
    }

    @Override
    public List<TicketDto> getAvailableTickets(){
        return ticketRepository.findAllByIsAvailableIsTrue().stream().map(ticket -> TicketMapper.ticketToTicketDto(ticket, new TicketDto())).toList();
    }

    @Override
    public void removeTicketsOfTour(Long tourId) {
        ticketRepository.deleteTicketsByTourId(tourId);
    }

    @Override
    public void orderTicket(TicketOrderDto ticketOrderDto) {
        TicketOrder ticketOrder = ticketOrderRepository.save(TicketOrderMapper.ticketOrderDtoToTicketOrder(ticketOrderDto, new TicketOrder()));
        orderTickerEvents(ticketOrder);
        log.info("Saved ticket order : {}",ticketOrder);
    }

    private void orderTickerEvents(TicketOrder ticketOrder) {
        Double tickerPrice = ticketRepository.findById(ticketOrder.getTicketId()).map(Ticket::getPrice).orElseThrow(
                ()-> new ResourceNotFoundException("Ticket order", "ticketId", ticketOrder.getTicketId().toString())
        );
        TicketOrderMsgDto ticketOrderMsgDto = new TicketOrderMsgDto(
                ticketOrder.getOrderId(),
                tickerPrice,
                ticketOrder.getUserEmail()
        );
        log.info("Order Ticket events: {}",ticketOrderMsgDto);
        boolean result = streamBridge.send("orderTicketEvents-out-0", ticketOrderMsgDto);
        log.info("Result of order ticket: {}",result);
    }

    @Override
    @Transactional
    public void changeStatus(OrderStatusMsgDto orderStatusMsgDto) {
        TicketOrder ticketOrder = ticketOrderRepository.findByOrderId(orderStatusMsgDto.orderId()).map(
                order->{
                    order.setStatus(orderStatusMsgDto.status());
                    log.info("Ticket order status changed : {}",order);
                    return ticketOrderRepository.save(order);
                }
        ).orElseThrow(
                ()-> new ResourceNotFoundException("Ticket order", "orderId", orderStatusMsgDto.orderId().toString())
        );
        ticketRepository.findById(ticketOrder.getTicketId()).map(
                ticket ->{
                    ticket.setIsAvailable(false);
                    log.info("Ticket is unavailable now : {}",ticket);
                    return ticketRepository.save(ticket);
                }
        ).orElseThrow(
                ()-> new ResourceNotFoundException("Ticket order", "ticketId", ticketOrder.getTicketId().toString())
        );
    }

}
