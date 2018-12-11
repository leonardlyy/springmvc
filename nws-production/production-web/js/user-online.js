var httpURL =httpUrl + "common/up-online-user";
var user=localStorage.getItem('user');
var userData=JSON.parse(user);
function upOnlineUser() {
    $.get(httpURL,
        {
            userId : userData.data.user.id,
            username: userData.data.user.username,
            name: userData.data.user.name,
        },function (response) {
            console.log("online username is " + userData.data.user.username)

        });
}
upOnlineUser();
setInterval(function(){
    upOnlineUser()
},1000*60*5);

