let stompClient = null;
let username = 'noname';

function connect() {
    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        username = frame.headers['user-name'];
        stompClient.subscribe('/user/chat_out/receive_message', function (payload) {
            console.log(payload);
            let msg = JSON.parse(payload.body);
            $("ul.chat").append(buildMessageHtml(msg.username, msg.message, false));
        });

        stompClient.subscribe('/chat_out/receive_message', function (payload) {
            console.log("Broadcast message " + payload);
            let msg = JSON.parse(payload.body);
            $("ul.chat").append(buildMessageHtml(msg.username, msg.message, false));
        });
    });
}

function sendMessage() {
    let textInput = $("#btn-input");
    stompClient.send("/chat_in/send_message", {},
        JSON.stringify({
            'username' : username,
            'message': textInput.val()
        }));
    $("ul.chat").append(buildMessageHtml(username, textInput.val(), true));
    textInput.val('');
}

function buildMessageHtml(username, message, direction) {
    return  '<li class="' + (direction ? 'left' : 'right') + ' clearfix">' +
        '<span class="chat-img ' + (direction ? 'float-left' : 'float-right') + '">' +
        '<img src="http://via.placeholder.com/30/55C1E7/fff&text=U" alt="User Avatar" class="rounded-circle"/>' +
        '</span>' +
        '<div class="chat-body clearfix">' +
        '<div class="header">' +
        '<strong class="' + (direction ? '' : '') + ' primary-font">' + username + '</strong>' +
        '<p>' + message + '</p>' +
        '</div>' +
        '</div>' +
        '</li>'
}

$(function () {
    $( "#btn-chat" ).click(function() { sendMessage(); });
});

connect();