theme: /
    state: Cancel
        q!: * $cancel *
        if: !$session.eventId
            script:
                $reactions.answer("Напоминаний нет");
        else:
            script:
                // $pushgate.cancelEvent - удаляет напоминание по указанному id
                #$reactions.answer("Будет удалено напоминание: " + $session.eventId);
                $pushgate.cancelEvent($session.eventId);
                // удаление свойства из переменной &client
                delete $session.eventId;
                // отправка сообщения пользователю
                $reactions.answer("Напоминание удалено");
        go!: /

    state: Reminder
        q!: * $launch *
        a: Я могу поставить напоминание на просмотр лекции. Напиши, в какое время тебе будет удобно, например: Сегодня в 19" или "Завтра в шесть вечера". Убрать напоминание можно командой "стоп" или "удалить".
                
                # стейт для установки времени по желанию пользователя.
        state: SetReminder
            intent: /SetReminder
                # если напоминание уже запущено, то удаляем его (в этой версии мы работаем только с одним напоминанием)
            if: $session.eventId
                script:
                    #$reactions.answer("Удалено: " + $session.eventId);
                    $pushgate.cancelEvent($session.eventId);
                    delete $session.eventId;
            script:
                $session.reminderTime = $parseTree["_duckling.time"];
                #$reactions.answer("время: {{$session.reminderTime.value}}");
                var reminderText = 'Для просмотра лекции перейдите по ссылке: {{links["video1"]}}'
                $temp.event = $pushgate.createEvent(
                    $session.reminderTime.value,
                    "reminderEvent",
                    {
                        text: reminderText
                    }
                );
                #запоминаем id напоминания, чтобы при повторном запуске, удалить старое напоминание
                $session.eventId = $temp.event.id; 
                #оповещаем пользователя о запуске напоминания
                $temp.reminderTime = moment($session.reminderTime.value).locale("ru").calendar();
                $reactions.answer("Хорошо! {{$temp.reminderTime}} я напомню посмотреть лекцию");
                #$reactions.answer("Напоминание: {{$session.eventId}}");
            go!: /MainMenu
                
                #стейт обрабатывает сработавшее напоминание, заводит "будильник" на это же время на следующий день.
    state: nextDayRemind
        event!: reminderEvent
        script:
            var reminderText = $request.rawRequest.eventData.text;
            # новая дата для нового напоминания. Тк старое всегда отрабатывает один раз и нужно создавать новые (к старой дате + 1 день)
            $session.reminderTime.value = moment($session.reminderTime.value).add(3, "minute").format();
            # выводим напоминание в назначенное время для пользователя
            $reactions.answer($request.rawRequest.eventData.text);
            $temp.event = $pushgate.createEvent(
                $session.reminderTime.value,
                "reminderEvent",
                {
                    text: reminderText
                }
            );
            // запоминаем id напоминания, чтобы при повторном запуске, удалить старое напоминание
            $session.eventId = $temp.event.id; 
            // оповещаем пользователя о запуске напоминания
            $temp.reminderTime = moment($session.reminderTime.value).locale("ru").calendar();
            $reactions.answer("Хорошо! {{$temp.reminderTime}} я напомню посмотреть лекцию");
            #$reactions.answer("Напоминание: {{$session.eventId}}");