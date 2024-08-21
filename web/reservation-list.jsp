<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Reservation List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <!-- Include jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- Include DataTables CSS and JS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <!-- Include DatePicker CSS and JS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                padding: 20px;
            }
            h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            .filter-container {
                margin-bottom: 20px;
                text-align: center;
            }
            .filter-container label {
                margin-right: 10px;
            }
            .filter-container input {
                margin-right: 20px;
            }
            table.dataTable {
                width: 100%;
                margin: 0 auto;
                border-collapse: collapse;
            }
            table.dataTable thead th {
                background-color: #007BFF;
                color: white;
                text-align: left;
                padding: 10px;
            }
            table.dataTable tbody tr {
                background-color: white;
                border-bottom: 1px solid #ddd;
            }
            table.dataTable tbody tr:hover {
                background-color: #f1f1f1;
            }
        </style>
    </head>
    <body>

        <h1>Reservation List</h1>

        <div class="filter-container">
            <label for="from">From:</label>
            <input type="text" id="from" name="from" value="${from}">
            <label for="to">To:</label>
            <input type="text" id="to" name="to" value="${to}">
            <button id="filterButton" class="btn btn-primary">Filter</button>
        </div>

        <table id="reservationTable" class="display">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Total Cost</th>
                    <th>Reservation Date</th>
                    <th>User</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Table body will be populated by JavaScript -->
            </tbody>
        </table>
        <input type="hidden" value='${reservations}' id="reservations" />
        <script>
            $(document).ready(function () {
                // Initialize DataTable
                var table = $('#reservationTable').DataTable();

                // Initialize DatePicker
                $("#from, #to").datepicker({
                    dateFormat: "yy-mm-dd"
                });

                // Parse JSON data from request attribute
                var reservationsJson = document.getElementById("reservations").value; // This will insert the JSON string into JavaScript
                var reservations = JSON.parse(reservationsJson);

                // Populate DataTable with reservation data
                reservations.forEach(function (reservation) {
                    table.row.add([
                        reservation.id,
                        reservation.totalCost + "$",
                        reservation.reservationDate,
                        reservation.userMail,
                        reservation.status == 0 ? 'SUBMITTED' : (reservation.status == 1 ? 'SUCCESS' : 'CANCELLED'),
                        '<button class="btn btn-primary">View Details</button>'
                    ]).draw(false);
                });

                // Filter button click event
                $('#filterButton').click(function () {
                    let from = $('#from').val();
                    let to = $('#to').val();
                    if (from && to) {
                        window.location.href = 'staff-reservations?from=' + from + '&to=' + to;
                    } else {
                        alert('Please select both dates.');
                    }
                });
            });
        </script>

    </body>
</html>

