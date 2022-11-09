package com.CourierManageSystem.backend.util;

import com.CourierManageSystem.backend.entity.Address;
import com.CourierManageSystem.backend.entity.Outlets;
import com.CourierManageSystem.backend.mapper.AddressMapper;
import com.CourierManageSystem.backend.mapper.OutletsMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class AddressMatchOutlets {
    @Autowired
    OutletsMapper outletsMapper;
    @Autowired
    AddressMapper addressMapper;
    public Long Match(Long delivery_address_id)
    {
        System.out.println("deliveryid="+delivery_address_id);
        System.out.println(addressMapper.selectList(null).toString());
        int min_i=0;
        double min=1000000000,present;
        Address delivery_address=addressMapper.selectById(delivery_address_id);
        double lng1=Double.parseDouble(delivery_address.getLocation().split(",")[0]),lng2;
        double lat1=Double.parseDouble(delivery_address.getLocation().split(",")[1]),lat2;
        List<Outlets> outlets_list=outletsMapper.selectList(null);
        for (int i=0;i<outlets_list.size();i++)
        {
            lng2=Double.parseDouble(outlets_list.get(i).getLocation().split(",")[0]);
            lat2=Double.parseDouble(outlets_list.get(i).getLocation().split(",")[1]);
            present=LocationUtils.getDistance(lng1,lat1,lng2,lat2);
            if(present<min)
            {
                min=present;
                min_i=i;
            }
        }
        return outlets_list.get(min_i).getId();
    }
}
