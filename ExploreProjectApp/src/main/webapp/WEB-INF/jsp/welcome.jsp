<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<style>
#loading-div-background{
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    background: #fff;
    width: 100%;
    height: 100%;
}

#loading-div{
    width: 300px;
    height: 150px;
    background-color: #fff;
    border: 5px solid #1468b3;
    text-align: center;
    color: #202020;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -150px;
    margin-top: -100px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    behavior: url("/css/pie/PIE.htc"); /* HANDLES IE */
}
</style>

<script type="text/javascript">
    $(document).ready(function (){
        $("#loading-div-background").css({ opacity: 1.0 });
    });

    function ShowProgressAnimation(){
        $("#loading-div-background").show();
    }
</script>
</head>
<html lang="en">

<body>

  <form method="POST" action="uploadFile.html" enctype="multipart/form-data">
    File to upload: <input type="file" name="file">
    <input type="submit" id="submit" name="submit" class="button" onclick="ShowProgressAnimation();"> 
  </form> 
  
  <div id="loading-div-background">
  <div id="loading-div" class="ui-corner-all">
    <img style="height:32px;width:32px;margin:30px;" src="./images/loading.gif" alt="Loading.."/>
    <br>PROCESSING. PLEASE WAIT...
  </div>
</div>
  
  <br>
  <br>
  <a href="/queries">Query Templates</a>
  <br>
  <a href="/log/log4j">log4j queries</a>
  <br>
  <a href="/startup/TEST_2016-04-13_11_58.log"> hibernate startup queries</a>
  <br>
  <a href="/log/xxxxxx.log"> Current queries</a>
  <br>
  <a href="/find/CPMG_2016-05-13_15_49.log/JdbcCoordinatorImpl"> search queries</a>
  <br>
  <a href="/error/xxxxx.log/"> Find Errors</a>
  <br>
  <a href="/ajax.html"> Ajax Call</a>
  <form action="/log">
    <input type="input" name="name">
   <input type="submit" id="submit" name="submit" class="button" onclick="ShowProgressAnimation();">
  </form>
</body>

</html>
