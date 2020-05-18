package com.wang.smart.service;

import com.wang.core.common.ErrorCodes;
import com.wang.core.common.ResultCode;
import com.wang.smart.utils.SmartUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FaceService  {

    public ResultCode compare(String faceA, String faceB,String imageType) {
        if(StringUtils.equals(imageType,SmartUtils.BASE64)){
            Double d=null;
            /*try {
                d=SmartUtils.compareFace(faceA,faceB);
            } catch (IOException e) {
                log.info("The pic compare is not success!faceA:{},faceB:{}.",faceA,faceB);
            }*/
            log.info("The pic compare is success!faceA:{},faceB{},simlarity:{}.",faceA,faceB,d);
            return new ResultCode(ErrorCodes.OK,d);
        }else if(StringUtils.equals(imageType,SmartUtils.BASE64)){
return null;
        }else {
            return ResultCode.error(ErrorCodes.DECRYPT_PARAMETERS_ERROR.getErrorNo(),"The image type "+imageType+" does not exist!");
        }

    }


    public ResultCode addFaceLib(String face) {
        return null;
    }
}
