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

    $('#mytab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')

        var text = $(this).text();
        console.log(text);

        if(text==="表格"){
            console.log("是表格");
            // 刷新显示表格
            $.post("/servlet/db", {
                    action: "query",
                    mode: "table"
                },
                function(data,status){
                    $(".div_table").html(data);
                    console.log(data);
                    console.log(status);
            });
        }else if(text==="卡片"){
            console.log("是卡片");
            // 刷新显示卡片列表
            $.post("/servlet/db", {
                    action: "query",
                    mode: "card"
                },
                function(data,status){
                    $(".div_table").html(data);
                    console.log(data);
                    console.log(status);
            });
        }else if(text==="云图"){
            console.log("是云图");
        }else if(text==="新增"){
            console.log("弹出新增对话框");
            $("#dialog").show();
        }else{
            console.log("不识别的tab");
        }

    })

/*
    // 废弃，但不删除，因为这个知识别没入库。
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
*/

/*
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
*/

    $("#btn_get").click(function(){
        console.log("clicked.");
        alert("you clicked get button from out js.");
    });

    /* 由 bootstrap javascript 插件的模态对话框实现了，这里的旧方案供参考，暂不删除 */
//    $("#btn_add").click(function(){
//        console.log("clicked.");
////        alert("you clicked add button");
//        $("#dialog").show();
//    });

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
        $("#btn-selected-visit").html(value);
        $("#btn-selected-visit").attr("data-value", value);
    });

    $("#list-status li").live("click", function(){
        console.log("you select <li> element");
        var value = $(this).attr("data-value");
        console.log(value);
        $("#btn-selected-status").html(value);
        $("#btn-selected-status").attr("data-value", value);
    });

/* bootstrap 的模态对话框自动实现了取消关闭对话框功能，以下代码方案供参考学习，暂时不删除 */
//    $("#btn_cancel_in_dialog").click(function(){
//        console.log("clicked btn_cancel_in_dialog.");
//        $("#dialog").hide();
//    });

    $("#btn_commit_in_dialog").click(function(){
        // hide dialog first.
        $("#dialog").hide();
        // refresh list.
        var visitLevelValue = $("#btn-selected-visit").attr("data-value");
        var statusValue = $("#btn-selected-status").attr("data-value");
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

        $('#addModalDialog').modal('toggle')
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

    /* ---------- 自动回到顶部 start ------- */
    //首先将#back-to-top隐藏
    $("#back-to-top").hide();
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
    $(function () {
        $(window).scroll(function(){
            if ($(window).scrollTop()>100){
                $("#back-to-top").fadeIn(500);
            } else {
                $("#back-to-top").fadeOut(500);
            }
        });

        //当点击跳转链接后，回到页面顶部位置
        $("#back-to-top").click(function(){
            $('body,html').animate({scrollTop:0},500);
            return false;
        });
    });
    /* ---------- 自动回到顶部 end ------- */
});