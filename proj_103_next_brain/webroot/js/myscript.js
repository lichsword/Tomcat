$(document).ready(function(){

    $("#dialog").hide();

    /*这个 get 方法被 post 替代了，但不要删除，供以后的 get 方法参考用。 */
//    $("#btn_mode_table").click(function(){
//        $.get("/servlet/db",
//        function(data,status){
//            $(".div_table").html(data);
//            console.log(data);
//            console.log(status);
//        });
//    });

    $().dropdown('toggle');
    $('.dropdown-toggle').dropdown();
    $('#menu').dropdown('toggle')

    $("#btn_mode_table").click(function(){
        $.post("/servlet/db", {
                action: "query",
                mode: "table"
            },
            function(data,status){
    //            $(".div2").append(data);
    //            $(".div2").replaceWith(data);
                $(".div_table").html(data);
                console.log(data);
                console.log(status);
        });
    });

    $("#btn_mode_card").click(function(){
        $.post("/servlet/db", {
                action: "query",
                mode: "card"
            },
            function(data,status){
    //            $(".div2").append(data);
    //            $(".div2").replaceWith(data);
                $(".div_table").html(data);
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

    /**
     * #list-visit li 表示：id 为 list-visit 的 子 li 元素
     * 进行动态绑定 click 事件.
     */
    $("#list-visit li").live("click",function(){
        console.log("you select <li> element");
        var value = $(this).attr("data-value");
        console.log(value);
        $("#selected-visit").html(value);
    });

    $("#list-status li").live("click", function(){
        console.log("you select <li> element");
        var value = $(this).attr("data-value");
        console.log(value);
        $("#selected-status").html(value);
    });

    $("#btn_cancel_in_dialog").click(function(){
        console.log("clicked btn_cancel_in_dialog.");
        $("#dialog").hide();
    });

    $("#btn_commit_in_dialog").click(function(){
        // hide dialog first.
        $("#dialog").hide();
        // refresh list.
        var visitLevelValue = $("#input_visit_level").attr("value");
        var statusValue = $("#input_status").attr("value");
        var questionValue = $("#input_question").attr("value");
        var descValue = $("#input_desc").attr("value");
        var labelsValue = $("#input_labels").attr("value");
        var truthValue = $("#input_truth").attr("value");
        var patternValue = $("#input_pattern").attr("value");
        var referenceValue = $("#input_reference").attr("value");
        var exampleValue = $("#input_example").attr("value");
//        $.post("/servlet/db", { name: "John lichsword 1  2   3\nkernel,hello-world", time: "2pm" },
        $.post("/servlet/db", {
                action: "insert",
                visit_level: visitLevelValue,
                status:  statusValue,
                question: questionValue,
                desc: descValue,
                labels: labelsValue,
                truth: truthValue,
                pattern: patternValue,
                reference: referenceValue,
                example: exampleValue
            },
            function(data,status){
    //            $(".div2").append(data);
    //            $(".div2").replaceWith(data);
                $(".div_table").html(data);
                console.log(data);
                console.log(status);
        });
    });

    /* 这里仍有问题，没有实现动态绑定 */
    $("button").live("click",function(){
        // debug print
//        console.log("clicked");
        // get id
//        console.log($(this));
//        $(this).attr("data-id");
        var id = $(this).attr("data-id");
        if(id === undefined){
            return;// do nothing
        }else{
            console.log(id);
            $.post("/servlet/db", {
                action: "delete",
                id: id
            },
            function(data, status){
               $(".div_table").html(data);
               // debug print.
               console.log(data);
               console.log(status);
            });
        }
    });
});