(function () {
    var token =  $('input[name="csrfToken"]').attr('value');

    var form = $("#addMember");
    form.on('submit', function(e){
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
            data: form.serialize(),
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

})();