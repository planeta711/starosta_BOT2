theme: /
    # отладочный стейт, посмотреть, есть ли напоминание. если есть - отобразиться его номер    
    state: ViewRemind
        q!: $regex</view>
        script:
            $reactions.answer("Напоминание: {{$session.eventId}}");  

    # отладочный стейт Reset для сброса переменных, закрытия старой и открытия новой сессии
    state: Reset
        q!: $regex</reset>
        script: 
            $context.client = {};
            $jsapi.stopSession();
            $reactions.newSession({message: "/start", data: $request.data}); 
            
    # сервисный стейт для получения id_group
    state: getID
        q!: $regex</getid>
        script:
            if ($request.channelType.indexOf("chatwidget") > -1) {
                $reactions.answer("id чата: {{$request.channelUserId}}");
            } else {
                $reactions.answer("id чата: {{$request.data.chatId}}");
            }