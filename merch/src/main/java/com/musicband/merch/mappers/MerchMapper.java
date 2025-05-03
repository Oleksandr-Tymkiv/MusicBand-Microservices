package com.musicband.merch.mappers;

import com.musicband.merch.dto.MerchDto;
import com.musicband.merch.entity.Merch;

public class MerchMapper {

    public static Merch merchDtoToMerch(MerchDto merchDto, Merch merch) {
        merch.setTitle(merchDto.getTitle());
        merch.setPrice(merchDto.getPrice());
        merch.setCategory(merchDto.getCategory());
        merch.setDescription(merchDto.getDescription());
        return merch;
    }

    public static MerchDto merchToMerchDto(Merch merch, MerchDto merchDto) {
        merchDto.setTitle(merch.getTitle());
        merchDto.setPrice(merch.getPrice());
        merchDto.setCategory(merch.getCategory());
        merchDto.setDescription(merch.getDescription());
        return merchDto;
    }

}
