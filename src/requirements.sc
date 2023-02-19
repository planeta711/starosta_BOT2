# запускаемые модули/библиотеки встроенных функций
require: slotfilling/slotFilling.sc
    module = sys.zb-common

# подключение болталки offTopic
require: newOfftopic/newOfftopic.sc
    module = sys.zb-common  

# подключение внешней библиотеки для получения времени
require: dateTime/moment.min.js
    module = sys.zb-common 

# подключение словарей
#словарь для хранения текстов ответов
require: dicts/answers.yaml
    var = answers
    name = answers
 
#словарь для хранения ссылок   
require: dicts/links.yaml
    var = links
    name = links
    
#словарь для хранения текста напоминаний   
require: dicts/reminder.yaml
    var = reminder
    name =  reminder  
    
# файлы сценария (код разделен на модули)
# стейт для обработки события telegramCallbackQuery
require: callback.sc

# файлы с функциями на JS
require: functions.js 

# файл с отправкой домашних заданий
require: homework.sc

# файл сценария с паттернами локальных переменных
require: localPatterns.sc

# стейты для запуска и обработки напоминаний
require: reminder.sc

# стейты для отладки и тестирования
require: servicezone.sc
