//функция отправки сообщений куратору
function sendMessageToMentor(message) {
    var id = $jsapi.context().injector.mentor_chat_id;
    var token =  $jsapi.context().injector.bot_token;
    var url = "https://api.telegram.org/bot"+token+"/sendMessage";
    var options = {
        body: {
            "chat_id": id, 
            "text": message 
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}

function postDocument(clientid, link, caption) {
    var token = $jsapi.context().injector.bot_token;
    var url = "https://api.telegram.org/bot"+token+"/sendDocument";
    var options = {
        dataType: "json",
        body: {
            "chat_id": clientid,
            "document": link,
            "caption": caption
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}