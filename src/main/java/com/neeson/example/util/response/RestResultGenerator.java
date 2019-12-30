package com.neeson.example.util.response;


import lombok.extern.slf4j.Slf4j;

/**
 * Created by daile on 2017/8/24.
 */
@Slf4j
public class RestResultGenerator {

//    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd") .registerTypeAdapterFactory( HibernateProxyTypeAdapter.FACTORY).create() ;
//    private static Gson gson  =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    /**
     * 生成响应成功的(不带正文)的结果
     * @param message
     * @return
     */
    public static ResponseResult genResult(String message){
        ResponseResult responseResult = ResponseResult.newInstance();
        responseResult.setCode(0);
        responseResult.setMessage(message);
        return responseResult;
    }

    /**
     * 生成响应成功的(带正文)的结果
     * @link ResponseResult
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> genResult(T data,String message){

        ResponseResult<T> responseResult = ResponseResult.newInstance();
        responseResult.setCode(0);
        responseResult.setMessage(message);
        responseResult.setData(data);

        logResult(responseResult);
        return responseResult;
    }

    private static <T> void logResult(ResponseResult<T> responseResult) {
        if (log.isDebugEnabled()){
            log.debug("------> result:{}",responseResult);
        }
    }

    /**
     * 生成响应失败的结果
     * @param errorEnum
     * @return
     */
    public static ResponseResult genErrorResult(ResponseErrorEnum errorEnum){
        ResponseResult responseResult = ResponseResult.newInstance();
        responseResult.setCode(-100);
        responseResult.setErrorInfo(errorEnum);
        logResult(responseResult);
        return responseResult;
    }

    public static ResponseResult genErrorResult(String message){
        ResponseResult result = ResponseResult.newInstance();
        result.setCode(-100);
        result.setMessage(message);
        logResult(result);

        return result;
    }

    public static <T> ResponseResult genErrorResult(String message,T data){
        ResponseResult result = ResponseResult.newInstance();
        result.setCode(-100);
        result.setMessage(message);
        result.setData(data);
        logResult(result);

        return result;
    }


}
