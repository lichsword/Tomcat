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

    $("#btn_commit_in_dialog").click(function(){
        // hide dialog first.
        $("#dialog").hide();
        // refresh list.
        var titleValue = $("#input_title").attr("value");
        var summaryValue = $("#input_summary").attr("value");
        var labelsValue = $("#input_labels").attr("value");
//        $.post("/servlet/db", { name: "John lichsword 1  2   3\nkernel,hello-world", time: "2pm" },
        $.post("/servlet/db", {
            title: titleValue,
            summary:  summaryValue,
            labels: labelsValue
        },
        function(data,status){
            var element=document.getElementById("div1");
            $(".div2").append(data);
            console.log(data);
            console.log(status);
        });
    });
});