package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.project.model.entity.InterfaceInfo;

/**
* @author CYX
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-03-31 17:04:29
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
