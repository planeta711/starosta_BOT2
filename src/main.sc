require: requirements.sc

theme: /

    state: Start
        q!: $regex</start>
        q!: привет
        script:
            # устанавливаем часовой пояс
            #$reactions.setClientTimezone("Europe/Ulyanovsk");
            $reactions.setClientTimezone("Europe/Moscow");
            # получаем user.name
            if ($request && $request.rawRequest && $request.rawRequest.message && $request.rawRequest.message.from && $request.rawRequest.message.from.username) {
            $client.username = "@"+$request.rawRequest.message.from.username;
            }
            # запоминаем user id 
            if ($request && $request.rawRequest && $request.rawRequest.message && $request.rawRequest.message.from && $request.rawRequest.message.from.id) {
                $client.user_id = $request.rawRequest.message.from.id
            }    
        a: Привет! Я виртуальный помощник ЦДПО МФТИ программы Conversation AI.\nВернуться в главное меню ты всегда можешь командой "эй" 
        go!: /MainMenu
        
    state: MainMenu
        q!: эй
        a: Выбери, что хочешь сейчас сделать:
        inlineButtons:
            {text: "Посмотреть видеолекции", callback_data: "Video"}
            {text: "Скачать конспекты лекций", callback_data: "Conspectus"}
            {text: "Ознакомиться с расписанием", callback_data: "Schedule"}  
            {text: "Отправить задание на проверку", callback_data: "Homework"}
            {text: "Установить напоминание", callback_data: "Reminder"}
            {text: "Выход", callback_data: "Bye"}
    
            # инлайнкнопки будут выведены двумя строками
        state: Conspectus
            q: * $conspect *
            a: Выберите презентацию к лекции номер:
            inlineButtons:
                { text: "1", callback_data: "1" }
                { text: "2", callback_data: "2" }
                { text: "3", callback_data: "3" }
                { text: "4", callback_data: "4" }
                { text: "5", callback_data: "5" }
            a: для возврата в Меню нажмите "Отмена"
            inlineButtons:
                { text: "6", callback_data: "6" }
                { text: "7", callback_data: "7" }
                { text: "8", callback_data: "8" }
                { text: "Отмена", callback_data: "No" }
            
        ####################################################################
        #Два демо-стейта. Отрабатывают 2 кнопки в Чат-Виджете. Будут удалены
            state: Link6
                q: 6
                a: {{links["presentation6"]}}
            
            state: Otmena
                q: Отмена
                go!: /MainMenu
        ####################################################################                    
            state: NoButton
                event: noMatch
                a: Для выбора воспользуйтесь кнопками.
                go!: MainMenu/Video/Yes
    
        state: Schedule
            q!: * $schedule *
            a: Ссылка на расписание: {{links["schedule"]}}
            
    state: Bye
        q!: (пока/выход)
        a: До свидания! Напиши "привет" или "эй", когда захочешь вернуться.\n Всегда рад помочь! 

    state: NoMatch ||noContext = true
        event!: noMatch
        a: Для связи с нами вы можете зайти на сайт https://coda.io/d/_d3IcXJ0R0ky/_sulQq

            # фильтрация обсценной лексики. всегда найдем, что ответить хаму
    state: Obscene || noContext = true
        q!: * @mlps-obscene.obscene * 
        script:
            $temp.index = $reactions.random(answers.obscene.phrases.length);
        a: {{answers.obscene.phrases[$temp.index]}}
        
    state: Help
        q!: * (help/помог*/помощ*) *
        a: привет - я поздороваюсь в ответ :)\nэй - возврат в главное меню\nзапустить - перейти в режим установки напоминания\nстоп - отменить напоминание
          

    # state: /CatchAll    
    #     event!: noMatch
    #     random:
    #         a: Простите, я Вас не поняла. 
    #         a: Извините, я Вас не понимаю. Вы сказали: {{$request.query}}.
    #     random:
    #         a: Попробуйте ответить по-другому.
    #         a: Переформулируйте, пожалуйста, свой вопрос.
    #         go!: {{$session.lastState}}

    # state: SuggestMovie || modal = true
    #     random: 
    #         a: На какой фильм Вы хотите пойти?
    #         a: Что Вы хотели бы посмотреть?
    #         a: Какой фильм Вас интересует?
    #     if: $request.channelType === "telegram"
    #         inlineButtons:
    #             {text: "Властелин Колец: Братство кольца", url:"https://www.kinopoisk.ru/film/328/"}
    #             {text: "Гарри Поттер и философский камень", url:"https://www.kinopoisk.ru/film/689/"}
    #             {text: "Кольская сверхглубокая", url:"https://www.kinopoisk.ru/film/1334853/"}
    #         a: Выберите фильм из списка ниже:
    #         buttons:
    #             "Властелин колец: Братство Кольца"
    #             "Гарри Поттер и камушек"
    #             "Кольская сверхглубокая"    
    #     else:
    #         buttons:
    #             "Властелин колец: Братство Кольца"
    #             "Гарри Поттер и камушек"
    #             "Кольская сверхглубокая"