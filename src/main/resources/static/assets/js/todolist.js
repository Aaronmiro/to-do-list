
// var host = "localhost"
var host = "104.131.45.178"

$("input[type = 'text']").keypress(function (e) {
    if (e.which === 13) {
        var todoText = $(this).val();
        if (todoText ==="") {
            return
        }
        addOne(todoText);
        $(this).val("");
        $("ul").append("<li><span><i class='fas fa-trash'></i></span> " + todoText + "</li>");
    }
});

$("ul").on("click", "li", function (e) {
    $(this).toggleClass("completed");
    var value = $(this).text()
    var completed = $(this).hasClass("completed");
    if (completed === true) {
        oneDone(value)
    }else{
        ondUnDone(value)
    }
});


$("h1").on("click", "i", function (e) {
    $(this).toggleClass("fa-plus");
    $("input[type = 'text']").fadeToggle(300);
    $(this).toggleClass("fa-minus");
});


$("ul").on("click", "span", function (e) {
    $(this).parent().slideUp(400, function () {
        $(this).remove();
        var value = $(this).text()
        deleteOne(value)
    });
    e.stopPropagation();
});

function loadData() {
    $("li").remove();
    $.ajax({
        type: "get",
        url: "http://"+host+":8091/load",
        success: function (response) {
            var array = response.data
            for (let index = 0; index < array.length; index++) {
                const element = array[index];
                if (element.status == 1) {
                    $("ul").append("<li class='completed'><span><i class='fas fa-trash'></i></span> " + element.itemContent + "</li>")
                }else{
                    $("ul").append("<li><span><i class='fas fa-trash'></i></span> " + element.itemContent + "</li>")
                }

                
            }
        }
    });

}

function addOne(content) {
    $.ajax({
        type: "get",
        url: "http://"+host+":8091/addOne",
        data: {content: content},
        dataType: "json",
        success: function (response) {
        }
    });
}

function oneDone(content) {
    $.ajax({
        type: "put",
        url: "http://" + host +":8091/oneDone",
        data: { content: content },
        dataType: "json",
        success: function (response) {
        }
    });
}

function ondUnDone(content) {
    $.ajax({
        type: "put",
        url: "http://" + host +":8091/oneUnDone",
        data: { content: content },
        dataType: "json",
        success: function (response) {
        }
    });
}

function deleteOne(content) {
    $.ajax({
        type: "delete",
        url: "http://" + host +":8091/deleteOne",
        data: { content: content },
        dataType: "json",
        success: function (response) {

        }
    });
}

$(document).ready(function () {
    //初始化宽度、高度  
    loadData();
    var widthContainer = $(window).width()-50
    var widthH1 = 460 / 500 * widthContainer
    if (widthContainer >= 500) {
        $(".container").width(500);
        $("input").width(460);
    }else {
        $(".container").width(widthContainer);
        $("input").width(widthH1 - 11);
    }
    //当文档窗口发生改变时 触发  
    $(window).resize(function () {
        var widthContainer = $(window).width() -50
        var widthH1 = 460 / 500 * widthContainer
        if (widthContainer >= 500) {
            $(".container").width(500);
            $("input").width(460);
        } else {
            $(".container").width(widthContainer);
            $("input").width(widthH1 - 11);
        }
    })
})

