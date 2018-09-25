(function () {
    var form = $("#register-form");
    form.on('submit', function(e){
        if($('#password').val() != $('#matchingPassword').val()){
            e.preventDefault();
            alert("The passwords must match!")
        }
    });
})();