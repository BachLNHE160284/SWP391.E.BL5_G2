<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Dashboard Statistics</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 50px auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .header {
                text-align: center;
                margin-bottom: 20px;
            }
            .date-filter {
                display: flex;
                justify-content: space-around;
                margin-bottom: 20px;
            }
            .date-filter label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }
            .date-filter input[type="date"] {
                padding: 10px;
                width: 200px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
            }
            .statistic-box {
                display: flex;
                justify-content: space-around;
                margin-bottom: 20px;
            }
            .statistic-box div {
                background-color: #007bff;
                color: white;
                padding: 20px;
                border-radius: 5px;
                width: 30%;
                text-align: center;
            }
            .statistic-box div h2 {
                margin: 0;
                font-size: 24px;
            }
            .statistic-box div p {
                margin: 5px 0 0;
                font-size: 16px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 15px;
                text-align: left;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1>Dashboard Statistics</h1>
            </div>

            <div class="date-filter">
                <div>
                    <label for="newReservationFilter">New Reservation Filter Date:</label>
                    <input type="date" id="newReservationFilter" name="newReservationFilter" value="${newReservationFilter}">
                </div>
                <div>
                    <label for="revenueFilter">Revenue Filter Date:</label>
                    <input type="date" id="revenueFilter" name="revenueFilter" value="${revenueFilter}">
                </div>
            </div>

            <div class="statistic-box">
                <div>
                    <h2>${statistic.successReservationCount}</h2>
                    <p>Success Reservations</p>
                </div>
                <div>
                    <h2>${statistic.cancelledReservationCount}</h2>
                    <p>Cancelled Reservations</p>
                </div>
                <div>
                    <h2>${statistic.submittedReservationCount}</h2>
                    <p>Submitted Reservations</p>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Category Name</th>
                        <th>Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="revenue" items="${statistic.revenues}">
                        <tr>
                            <td>${revenue.category.category_name} </td>
                            <td>${revenue.revenue}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty statistic.revenues}">
                        <tr>
                            <td colspan="2">No data available</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
    <script>
        function redirectToDashboard() {
            var newReservationFilter = document.getElementById('newReservationFilter').value;
            var revenueFilter = document.getElementById('revenueFilter').value;
            window.location.href = 'dashboard?newReservationFilter=' + newReservationFilter + '&revenueFilter=' + revenueFilter;
        }

        document.addEventListener("DOMContentLoaded", function () {
            document.getElementById('newReservationFilter').addEventListener('change', redirectToDashboard);
            document.getElementById('revenueFilter').addEventListener('change', redirectToDashboard);
        });
    </script>
</html>
