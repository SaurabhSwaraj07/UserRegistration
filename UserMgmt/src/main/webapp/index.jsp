<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<link rel="stylesheet" href="css/bootstrap.css">
<style>
    body {
      background-color: #e9ecef; /* Decent background color */
    }
    .form-container {
      max-width: 600px;
      margin: 5px auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 10px; /* Border radius on all sides */
      background-color: #ffffff;
    }
    .form-title {
      text-align: center;
      margin-bottom: 20px;
    }
    .form-select {
      width: 100%;
      padding: 10px; /* Make the select box the same size as other inputs */
    }
    .btn {
      border-radius: 10px; /* Add border radius to buttons */
      font-size: 16px;
      padding: 10px 20px;
      margin: 5px; /* Add some margin to separate the buttons */
      min-width: 150px; /* Set a minimum width */
      height: 50px; /* Set a fixed height */
    }
    .btn-container {
      text-align: center; /* Center the buttons */
      margin-top: 20px; /* Add margin to top */
    }
    .btn-group {
      display: flex;
      justify-content: center;
    }
    .btn:hover {
      opacity: 0.8; /* Slight opacity change on hover for better look */
    }
    @media (max-width: 768px) {
      .btn-container {
        flex-direction: column;
      }
    }
  </style>
</head>
<body>
  <div class="container form-container">
    <h2 class="form-title">User Registration</h2> <!-- Title Added -->
    <form action="register" method="post">
      <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" required name="name1">
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <input type="email" class="form-control" id="email" required name="email1">
      </div>
      <div class="mb-3">
        <label for="mobile" class="form-label">Mobile</label>
        <input type="text" class="form-control" id="mobile" required name="mobile1">
      </div>
      <div class="mb-3">
        <label for="dob" class="form-label">Date of Birth</label>
        <input type="date" class="form-control" id="dob" required name="dob1">
      </div>
      <div class="mb-3">
        <label for="city" class="form-label">City</label>
        <select class="form-select" id="city" required name="city1">
          <option value="" disabled selected>Select your city</option>
          <option value="Bangalore">Bangalore</option>
          <option value="Patna">Patna</option>
          <option value="Varanasi">Varanasi</option>
          <option value="Darbhanga">Darbhanga</option>
        </select>
      </div>
      <div class="mb-3">
        <label class="form-label">Gender</label><br>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" id="male" name="gender1" value="male">
          <label class="form-check-label" for="male">Male</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" id="female" name="gender1" value="female">
          <label class="form-check-label" for="female">Female</label>
        </div>
        
      </div>
      <div class="btn-container d-flex justify-content-center">
        <button type="submit" class="btn btn-outline-primary mx-2">Submit</button>
        <button type="button" class="btn btn-outline-secondary mx-2" onclick="this.form.reset()">Cancel</button>
        <a href="showdata" class="btn btn-outline-warning mx-2">Show Users</a>
      
      </div>
    </form>
    
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>