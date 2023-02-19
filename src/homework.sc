theme: /
    
    state: Homework
        q: Отправить задание на проверку
        a: Нажми на скрепку (в окне набора сообщения). Выбери файл для отправки. Нажми "Открыть". В комментарии к файлу укажи свою фамилию и название работы. Нажми "Отправить".
        a: Либо размести ссылку на свою работу.

        state: ShiftfileEvent
            # событие fileEvent - событие отправки файла пользователем
            event: fileEvent
            script:            
                # сохраняем ссылку на файл с выполненной работой
                $session.filereport = $request.data.eventData[0].url;
                var message = "Студент " + $client.username + " отправил файл с выполненной работой. Ссылка на работу: " + $session.filereport
                # отправляем сообщение куратору
                $temp.response = sendMessageToMentor(message);
                if ($temp.response) {
                    $reactions.answer("Ваша работа успешно доставлена");
                } else {
                    $reactions.answer("Какие-то помехи на линии, продублируйте позже");
                }

            go!: /MainMenu    
            
            # стейт ловит ссылки студентов.    
        state: FileLink
            event: noMatch
            script:
                var message = "Студент " + $client.username + " отправил ссылку на работу: " + $request.query
                $temp.response = sendMessageToMentor(message);
                if ($temp.response) {
                    $reactions.answer("Ваше сообщение успешно доставлено");
                } else {
                    $reactions.answer("Какие-то помехи на линии, продублируйте позже");
                }
            go!: /MainMenu      