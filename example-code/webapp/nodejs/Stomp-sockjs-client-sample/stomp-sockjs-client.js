var url = 'http://localhost:9002'

var SockJS = require('sockjs-client'),
    Stomp  = require('stompjs'),
    socket = new SockJS(url + '/elidom/stomp'),

    client = Stomp.over(socket)

function connect(){
  client.connect({}, function(frame){
    console.log(frame)
    client.subscribe('/elidom/stomp/topic/greetings', function(greeting){
      console.log(greeting)
    })
    sendMessage()
  })
}

function sendMessage(){
  var name = 'Gideon'
  client.send('/hello', {}, JSON.stringify({ 'name': name }))
}

function disconnect(){
  if(client)
    client.disconnect()
}

function start(){
  connect()
}

start();