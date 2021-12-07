$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("HomePage.feature");
formatter.feature({
  "line": 1,
  "name": "Free CRM HomePage Feature",
  "description": "",
  "id": "free-crm-homepage-feature",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Verify user can access the home page",
  "description": "",
  "id": "free-crm-homepage-feature;verify-user-can-access-the-home-page",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@RegressionTest"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "user is on home page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "user verify title of the page",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "user verify logo of the page",
  "keyword": "Then "
});
formatter.match({
  "location": "HomePageTest.user_is_on_home_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "HomePageTest.user_verify_title_of_the_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "HomePageTest.user_verify_logo_of_the_page()"
});
formatter.result({
  "status": "skipped"
});
});