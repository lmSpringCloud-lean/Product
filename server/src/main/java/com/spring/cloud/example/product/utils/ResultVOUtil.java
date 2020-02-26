package com.spring.cloud.example.product.utils;

import com.spring.cloud.example.product.VO.ResultVO;
import org.springframework.util.StringUtils;

public class ResultVOUtil {
    public static ResultVO success(Object data) {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResultVO success() {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    public static ResultVO error(Integer code, String Msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        if (!StringUtils.isEmpty(Msg)) {
            result.setMsg(Msg);
        } else {
            result.setMsg("失败");
        }
        return result;
    }
}
