package com.song.ex.pay.controller.hanlder;

/**
 * com.song.ex.pay.controller.hanlder
 *
 * @author by Song
 * @date 2019/9/30 16:30
 */
public class TestDemo {


    /**
     * code : 200
     * data : {"msg":"本次导入：3个客户，预计导入成功：3个客户，成功导入：3个客户，重复：0个客户，话术变量错误：0个客户","successNum":3,"repeatNum":0,"placeFailNum":0}
     * resultMsg : 操作成功
     * errorStackTrace : null
     * requestId : null
     */

    private int code;
    private DataBean data;
    private String resultMsg;
    private Object errorStackTrace;
    private Object requestId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(Object errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }

    public Object getRequestId() {
        return requestId;
    }

    public void setRequestId(Object requestId) {
        this.requestId = requestId;
    }

    public static class DataBean {
        /**
         * msg : 本次导入：3个客户，预计导入成功：3个客户，成功导入：3个客户，重复：0个客户，话术变量错误：0个客户
         * successNum : 3
         * repeatNum : 0
         * placeFailNum : 0
         */

        private String msg;
        private int successNum;
        private int repeatNum;
        private int placeFailNum;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getSuccessNum() {
            return successNum;
        }

        public void setSuccessNum(int successNum) {
            this.successNum = successNum;
        }

        public int getRepeatNum() {
            return repeatNum;
        }

        public void setRepeatNum(int repeatNum) {
            this.repeatNum = repeatNum;
        }

        public int getPlaceFailNum() {
            return placeFailNum;
        }

        public void setPlaceFailNum(int placeFailNum) {
            this.placeFailNum = placeFailNum;
        }
    }
}
