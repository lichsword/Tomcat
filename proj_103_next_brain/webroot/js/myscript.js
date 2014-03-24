$(document).ready(function(){

    $("#btn_refresh").click(function(){
        $.get("/servlet/db",
        function(data,status){
            var element=document.getElementById("div1");
            $(".div2").append(data);
            console.log(data);
            console.log(status);
        });
    });

    $("#btn_get").click(function(){
        console.log("clicked.");
        alert("you clicked get button from out js.");
    });

    $("#btn_add").click(function(){
        console.log("clicked.");
        alert("you clicked add button");
    });

    $("#btn_delete").click(function(){
        console.log("clicked.");
        alert("you clicked delete button");
    });

});