package com.study.sockets;

/**
 * @Auther: wsf
 * @Date: 2023/5/30 15:52
 * @Description:定时每天10、14点定时推送订餐消息
 */
@Component
public class DailyMenuTask {

    @Resource
    private WebSocket webSocket;

    @Autowired
    private DailyMenuMapper dailyMenuMapper;

    @Scheduled(cron = "0 0 10,14 * * ?")
    public void pushDailyMenu() {
        DailyMenu dailyMenu = new DailyMenu();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        Date time = cal.getTime();
        dailyMenu.setFoodDate(time);
        List<DailyMenu> dailyMenus = dailyMenuMapper.selectByDate(dailyMenu);
        if (CollectionUtils.isNotEmpty(dailyMenus)) {
            //创建业务消息信息
            JSONObject obj = new JSONObject();
            // 业务类型
            obj.put(WebsocketConst.MSG_CMD, WebsocketConst.CMD_TOPIC);
            obj.put(WebsocketConst.MSG_ID, dailyMenus.get(0).getId());
            obj.put(WebsocketConst.MSG_TXT, "订餐发布");
            //全体发送
            webSocket.sendAllMessage(obj.toJSONString());
        }

    }


}

