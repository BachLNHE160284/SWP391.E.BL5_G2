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
            .modal-body img {
                max-width: 100%;
                height: auto;
            }
            .modal-content {
                border-radius: 10px;
                overflow: hidden;
            }

            .modal-header {
                background-color: #007BFF;
                color: white;
                border-bottom: none;
                padding: 20px;
            }

            .modal-title {
                font-size: 1.5rem;
                font-weight: bold;
            }

            .modal-body {
                padding: 20px;
            }

            .modal-body h5 {
                font-size: 1.25rem;
                margin-bottom: 15px;
                color: #333;
            }

            .modal-body p {
                font-size: 1rem;
                margin-bottom: 10px;
            }

            .modal-body .list-group-item {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 15px;
                margin-bottom: 10px;
                background-color: #f9f9f9;
            }

            .modal-body .list-group-item img {
                float: left;
                margin-right: 15px;
                border-radius: 5px;
            }

            .modal-footer {
                border-top: none;
                padding: 15px;
                text-align: right;
            }

            .modal-footer .btn-secondary {
                background-color: #6c757d;
                border: none;
            }

            .modal-footer .btn-secondary:hover {
                background-color: #5a6268;
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

        <!-- Reservation Detail Modal -->
        <div class="modal fade" id="reservationDetailModal" tabindex="-1" aria-labelledby="reservationDetailModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="reservationDetailModalLabel">Reservation Detail</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Basic Reservation Information -->
                        <h3>Basic Information</h3>
                        <p><strong>ID:</strong> <span id="reservationId"></span></p>
                        <p><strong>Customer Full Name:</strong> <span id="customerFullName"></span></p>
                        <p><strong>Customer Email:</strong> <span id="customerEmail"></span></p>
                        <p><strong>Customer Mobile:</strong> <span id="customerMobile"></span></p>
                        <p><strong>Reservation Date:</strong> <span id="reservationDate"></span></p>
                        <p><strong>Total Cost:</strong> <span id="totalCost"></span></p>
                        <p><strong>Status:</strong> <span id="statusReservation"></span></p><br/>

                        <!-- Receiver Information -->
                        <h3>Receiver Information</h3>
                        <p><strong>Full Name:</strong> <span id="receiverFullName"></span></p>
                        <p><strong>Gender:</strong> <span id="receiverGender"></span></p>
                        <p><strong>Email:</strong> <span id="receiverEmail"></span></p>
                        <p><strong>Mobile:</strong> <span id="receiverMobile"></span></p>
                        <p><strong>Address:</strong> <span id="receiverAddress"></span></p>

                        <!-- Reserved Services -->
                        <h5>Reserved Services</h5>
                        <ul id="reservedServicesList" class="list-group">
                            <!-- Reserved services will be populated here -->
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

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
                        '<button class="btn btn-primary view-details" data-id="' + reservation.id + '">View Details</button>'
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

                // Handle click on "View Details" button
                $('#reservationTable').on('click', '.view-details', function () {
                    var reservationId = $(this).data('id');
                    $.ajax({
                        url: 'staff-reservation-detail?reservationId=' + reservationId, // URL to fetch reservation details
                        type: 'GET',
                        dataType: 'json',
                        success: function (data) {
                            // Populate the modal with reservation details
                            $('#reservationId').text(data.reservationId);
                            $('#customerFullName').text(data.customerFullName);
                            $('#customerEmail').text(data.customerEmail);
                            $('#customerMobile').text(data.customerMobile);
                            $('#reservationDate').text(new Date(data.reservationDate).toLocaleDateString());
                            $('#totalCost').text(data.totalCost + "$");
                            $('#statusReservation').text(data.statusReservation == 0 ? 'SUBMITTED' : (data.statusReservation == 1 ? 'SUCCESS' : 'CANCELLED'));

                            $('#receiverFullName').text(data.receiverFullName);
                            $('#receiverGender').text(data.receiverGender ? 'Male' : 'Female');
                            $('#receiverEmail').text(data.receiverEmail);
                            $('#receiverMobile').text(data.receiverMobile);
                            $('#receiverAddress').text(data.receiverAddress);

                            // Clear existing services
                            $('#reservedServicesList').empty();
                            // Populate reserved services
                            data.reservedServices.forEach(function (service) {
                                $('#reservedServicesList').append(
                                        '<li class="list-group-item">' +
                                        (service.serviceThumbnail ? '<img src="' + service.serviceThumbnail + '" alt="Service Thumbnail" class="img-thumbnail" style="max-width: 100px; height: auto;" />' : '') +
                                        '<strong>' + service.serviceName + '</strong><br>' +
                                        'Category: ' + service.categoryName + '<br>' +
                                        'Unit Price: ' + service.unitPrice + '$<br>' +
                                        'Number of Persons: ' + service.numberOfPerson + '<br>' +
                                        'Total Cost: ' + service.serviceTotalCost + '$' +
                                        '</li>'
                                        );
                            });

                            // Show the modal
                            $('#reservationDetailModal').modal('show');
                        },
                        error: function () {
                            alert('Failed to fetch reservation details.');
                        }
                    });
                });
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    </body>
</html>
