
var hipsterJesus = {

  html: function() {
    return $.getJSON('http://hipsterjesus.com/api2/').then(function(data) {
      return data.text;
    });
  },

  paragraphs: function() {
    return this.html().then(function(html) {
      return html.replace(/<[^>]+>/g, "").split("");
    });
  }
};
