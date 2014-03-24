$(document).ready(function(){

    $("#dialog").hide();

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
//        alert("you clicked add button");
        $("#dialog").show();
    });

    $("#btn_delete").click(function(){
        console.log("clicked btn_delete.");
        alert("you clicked delete button");
    });

    $("#btn_cancel_in_dialog").click(function(){
        console.log("clicked btn_cancel_in_dialog.");
        $("#dialog").hide();
    });
});