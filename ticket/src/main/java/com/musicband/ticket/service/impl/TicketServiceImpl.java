package com.musicband.ticket.service.impl;

import com.musicband.ticket.dto.TicketDto;
import com.musicband.ticket.dto.TicketMsgDto;
import com.musicband.ticket.entity.Ticket;
import com.musicband.ticket.mapper.TicketMapper;
import com.musicband.ticket.repository.TicketRepository;
import com.musicband.ticket.service.TicketService;
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
    private final StreamBridge streamBridge;

    @Override
    public void addTicket(TicketDto ticketDto) {
        if(ticketRepository.findTicketByPlaceAndTourId(ticketDto.getPlace(), ticketDto.getTourId()).isPresent()){
            throw new IllegalStateException("Ticket with place"+ ticketDto.getPlace() +" for tour with "+ ticketDto.getTourId()+" already exists ");
        }
        Ticket savedTicket = ticketRepository.save(TicketMapper.ticketDtoToTicket(ticketDto,new Ticket()));
        newTicketsFotTour(savedTicket);
        log.info("Ticked saved : {}", savedTicket);
    }

    private void newTicketsFotTour(Ticket ticket) {
        TicketMsgDto ticketMsgDto = new TicketMsgDto(
                ticket.getPrice(),
                ticket.getPlace(),
                ticket.getIsPurchase(),
                ticket.getTourId()
        );
        log.info("New Ticket events: {}",ticketMsgDto);
        boolean result = streamBridge.send("newTicketsEvents-out-0", ticketMsgDto);
        log.info("Result of adding: {}",result);
    }

    @Override
    public void addTicketsForTour(Long tourId) {
        List<TicketDto> createdTickets = IntStream.range(0, DEFAULT_TICKET_COUNT)
                        .mapToObj(i -> TicketDto.builder()
                                .price(99.99)
                                .place("A"+i)
                                .isPurchase(false)
                                .tourId(tourId)
                                .build()
                        ).toList();

        createdTickets.forEach(this::addTicket);
    }

    @Override
    public boolean purchaseTicket(TicketDto ticketDto) {
        boolean isTickedPurchase = false;
        Ticket ticket = ticketRepository.findTicketByPlaceAndTourId(ticketDto.getPlace(), ticketDto.getTourId()).orElseThrow(
                ()-> new IllegalStateException("Ticket with place"+ ticketDto.getPlace() +" not found")
        );
        if(!ticket.getIsPurchase()){
            ticket.setIsPurchase(true);
            isTickedPurchase = true;
            ticketRepository.save(ticket);
        }
        return isTickedPurchase;
    }

    @Override
    public List<TicketDto> getTickets() {
        return ticketRepository.findAllByIsPurchaseIsFalse().stream().map(ticket -> TicketMapper.ticketToTicketDto(ticket, new TicketDto())).toList();
    }

    @Override
    public boolean returnTicket(TicketDto ticketDto) {
        boolean isTicketReturned = false;
        Ticket ticket = ticketRepository.findTicketByPlaceAndTourId(ticketDto.getPlace(), ticketDto.getTourId()).orElseThrow(
                ()-> new IllegalStateException("Ticket with place"+ ticketDto.getPlace() +" not found")
        );
        if(ticket.getIsPurchase()){
            ticket.setIsPurchase(false);
            isTicketReturned = true;
            ticketRepository.save(ticket);
        }
        return isTicketReturned;
    }

    @Override
    public void removeTicketsOfTour(Long tourId) {
        ticketRepository.deleteTicketsByTourId(tourId);
    }

}
