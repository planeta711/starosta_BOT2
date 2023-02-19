theme: /
        
    # Стейт обрабатывает данные, поступающие из ТГ при клике на инлайн-кнопку    
    state: ReceiptCallbackQuery
        event: telegramCallbackQuery
        if: $request.rawRequest.callback_query.data === "Video" 
            a: {{links["video1"]}}
        if: $request.rawRequest.callback_query.data === "Conspectus" 
            go!: /MainMenu/Conspectus
        if: $request.rawRequest.callback_query.data === "Schedule" 
            go!: /MainMenu/Schedule
        if: $request.rawRequest.callback_query.data === "Homework" 
            go!: /Homework            
        if: $request.rawRequest.callback_query.data === "Reminder" 
            go!: /Reminder
        if: $request.rawRequest.callback_query.data === "Bye" 
            go!: /Bye 
        if: $request.rawRequest.callback_query.data === "Yes" 
            go!: /MainMenu/Video/Yes
        if: $request.rawRequest.callback_query.data === "No" 
            go!: /MainMenu    

        if: $request.rawRequest.callback_query.data === "1"
            script: postDocument($client.user_id, links["presentation1"], "Лови первый конспект")
        if: $request.rawRequest.callback_query.data === "2" 
            script: postDocument($client.user_id, links["presentation2"], "Лови второй конспект")        
        if: $request.rawRequest.callback_query.data === "3" 
            script: postDocument($client.user_id, links["presentation3"], "Вот третий конспект")          
        if: $request.rawRequest.callback_query.data === "4" 
            script: postDocument($client.user_id, links["presentation4"], "Четвертый конспект")
        if: $request.rawRequest.callback_query.data === "5" 
            script: postDocument($client.user_id, links["presentation5"], "Пятый конспект")         
        if: $request.rawRequest.callback_query.data === "6" 
            script: postDocument($client.user_id, links["presentation6"], "Шестой конспект")            
        if: $request.rawRequest.callback_query.data === "7" 
            a: {{links["presentation7"]}}            
        if: $request.rawRequest.callback_query.data === "8" 
            script: postDocument($client.user_id, links["presentation8"], "Восьмой конспект. Не думал о торрентах?")    
       