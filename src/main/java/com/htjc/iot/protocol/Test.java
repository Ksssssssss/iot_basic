//package com.hoolai.iot.protocol;
//
///**
// * @author ksssss
// * @date 20-9-2
// */
//public class Test {
//
//        /**
//         * 端口号
//         * @return int
//         */
//        int port();
//
//        /**
//         * 超时离线时间 ms
//         * @return
//         */
//        long ttl();
//
//        /**
//         * 协议类型
//         * @return ProtocolType
//         */
//        ProtocolType protocolType();
//
//        /**
//         * 拆包
//         * @return ByteBuf
//         * @throws Exception
//         */
//        ByteBuf split(ByteBuf in) throws Exception;
//
//
//        /**
//         * 转化为原始报文
//         * @param unescapeBytes 反转义后的数据
//         * @return string
//         */
//        String getOriginLog(byte[] unescapeBytes);
//
//
//        /**
//         * 校验
//         * @return boolean
//         * @throws Exception
//         */
//        boolean validate(ByteBuf in) throws Exception;
//
//        /**
//         * 转义， 如 7e -> 7d 01
//         * @return ByteBuf
//         * @throws Exception
//         */
//        ByteBuf escape(ByteBuf out) throws Exception;
//
//
//        /**
//         * 反转义，如：7d 01  -> 7e
//         * @return ByteBuf
//         * @throws Exception
//         */
//        ByteBuf unescape(ByteBuf in) throws Exception;
//
//
//        /**
//         * 解析出消息头中必要属性
//         * @param in 反转义后的数据
//         * @return Header
//         * @throws Exception
//         */
//        Header parseHeader(ByteBuf in, Session session) throws Exception;
//
//        /**
//         * 解析出消息内容
//         * @param header 消息头
//         * @return Message
//         * @throws Exception
//         */
//        Message parseBody(Header header) throws Exception;
//
//
//        /**
//         * 回复设备上行消息
//         * @param message
//         */
//        void response(Message message);
//
//        /**
//         * 根据下行命令创建设备回复的识别标识
//         * @return ReplySign
//         * @throws Exception
//         */
//        ReplySign buildReplySign(ByteBuf out, Session session) throws Exception;
//
//        /**
//         * 匹配是否回复消息
//         * @return boolean
//         * @throws Exception
//         */
//        ReplySign replyMatch(Message message, Session session) throws Exception;
//
//
//        /**
//         * 生成控制指令
//         * @param command
//         * @return
//         * @throws Exception
//         */
//        ByteBuf buildCommand(Command command,Session session) throws Exception;
//
//
//        /**
//         * 可以查询，修改，控制的项目
//         * @return
//         */
//        List<Object> controllableItems();
//
//
//        /**
//         * 将message对象解析为数据库存储对象
//         * @param message 从mq拉取的Message对象
//         * @param device 设备的详细信息
//         * @param funcType 功能类型
//         * @return
//         */
//        DataBase coreParse(Message message, Device device, FuncType funcType);
//
//
//        /**
//         * 将database转为具体的监测数据
//         * @param funcType 功能类型
//         * @param data 基础数据解析结果
//         * @return
//         */
//        ConvertBase convert(FuncType funcType, DataBase data);
//
//    }
//
//}
