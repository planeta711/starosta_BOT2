MVP Чат-бот "Староста". 
Реализованные функции:

1. Дает ссылки на видеолекции для просмотра и ссылки на конспекты для скачивания.

2. Дает ссылку на расписание

3. Принимает файлы с выполненной работой или ссылкой на нее и отправляет 
   сообщение в чат Ментору для инициирования проверки. Для настройки нужно указать 
   mentor_chat_id и бот-токен в конфигурационном файле. Бота придется "подселить" 
   ментору в т.ч.
   
4. Устанавливает напоминание в удобное время для пользователя на просмотр лекций. 
   Далее "заводит" напоминание на следующий день в это же время. Удаляется словом "стоп".
   В этой версии напоминания выходят каждые 3 минуты. Легко поправить.
   Не стал здесь делать "40 напоминаний", работает всегда одно, дубли удаляются.
   
5. Понимает обсценную лексику. Не растеряется при ответе хаму.

6. Подключен оффтопик, из-за этого собственное приветствие-прощание скудное: 
   привет-пока-выход.
   
7. Чат-виджет поддерживает все функции, кроме отправки файла.

8. Нет автотестов, но есть сервисное меню servicezone.sc:
    /reset - для сброса переменных, закрытия старой и открытия новой сессии, 
    /getid - для получения id_group, 
    /view - позволяет посмотреть, есть ли напоминание и его номер
    /help - выдает список команд. Общий список слов в localPatterns.sc