(function () {
    var token =  $('input[name="csrfToken"]').attr('value');

    var add = $("#addMember");
    add.on('submit', function(e){
        e.preventDefault();
        if($('#password').val() != $('#matchingPassword').val()){
            alert("The passwords must match!")
            return;
        }
        $.ajax({
            url: '/owner/addMember',
            type: 'post',
            headers: {
                'X-CSRF-Token': token
            },
            data: add.serialize(),
            dataType: 'json',
            success: function (succeeded){
                location.reload()
                if (succeeded){
                    alert("Member registered!");
                }else {
                    alert("Registration failed!");
                }
            }
        });
    });

    var calendar = $("#addCalendar");
    calendar.on('submit', function(e){
        e.preventDefault();

        var startDate = document.getElementById("startDate").value;
        var endDate = document.getElementById("endDate").value;

        if(startDate > endDate) {
            alert("The end date must be after the start date!");
            return;
        }

        var today = new Date();
        var currentDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-';
        if(today.getDate()<10){
            currentDate += '0' + today.getDate();
        }else{
            currentDate += today.getDate();
        }

        if(startDate < currentDate){
            alert("The start date must not be in the past!");
            return;
        }

        $.ajax({
            url: '/owner/addCalendar',
            type: 'post',
            headers: {
                'X-CSRF-Token': token
            },
            data: calendar.serialize(),
            dataType: 'json',
            success: function (succeeded){
                location.reload();
                if (succeeded){
                    alert("Calendar registered!");
                }else {
                    alert("Calendar registration failed!");
                }
            }
        });
    });

    var day = $("#addDay");
    day.on('submit', function(e){
        e.preventDefault();

        $.ajax({
            url: '/owner/addDay',
            type: 'post',
            headers: {
                'X-CSRF-Token': token
            },
            data: day.serialize(),
            dataType: 'json',
            success: function (succeeded){
                location.reload();
                if (succeeded){
                    alert("Day registered!");
                }else {
                    alert("Day registration failed!");
                }
            }
        });
    });

})();