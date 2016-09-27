var url = 'https://spark-heroku-hello-world.herokuapp.com';

$.getJSON(url + '/hello', function (response) {
    $('#hello-message').text(response.message);
});