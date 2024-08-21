<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Settings List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .role-list-item {
            background-color: #007bff;
            color: white;
            padding: 8px 12px;
            border-radius: 5px;
            margin-bottom: 5px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Settings List</h1>
        
        <!-- Hidden input to store the JSON data -->
        <input type="hidden" id="settingsData" value='${settings}'>

        <!-- Table to display the data -->
        <table id="settingsTable" class="display">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>URL</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <!-- Popup Modal for Settings Detail -->
    <div class="modal fade" id="settingsDetailModal" tabindex="-1" aria-labelledby="settingsDetailModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="settingsDetailModalLabel">Settings Detail</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Pre-added Roles:</label>
                        <div id="roleList"></div>
                    </div>
                    <div class="form-group">
                        <label>Add New Role:</label>
                        <select id="roleSelect" class="form-control"></select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            // Get the JSON data from the hidden input
            var settingsData = JSON.parse($('#settingsData').val());
            
            // Initialize DataTable
            var table = $('#settingsTable').DataTable({
                data: settingsData,
                columns: [
                    { 
                        data: null,
                        render: function (data, type, row, meta) {
                            return meta.row + 1; // STT (index + 1)
                        }
                    },
                    { data: 'url' },  // URL column
                    {
                        data: null,
                        render: function (data, type, row, meta) {
                            return '<button class="btn btn-primary view-details" data-url="' + row.url + '">View Details</button>';
                        }
                    }
                ]
            });

            // Handle "View Details" button click
            $('#settingsTable tbody').on('click', '.view-details', function() {
                var url = $(this).data('url');

                // Send AJAX request to get settings detail
                $.ajax({
                    url: 'settings-detail',
                    method: 'GET',
                    data: { url: url },
                    success: function(response) {
                        var settingsDetail = JSON.parse(response);
                        var roles = settingsDetail.roles;
                        var settingsRoles = settingsDetail.settings.roles;

                        // Clear previous role list and role select options
                        $('#roleList').empty();
                        $('#roleSelect').empty();

                        // Populate the pre-added roles list with Bootstrap-styled badges
                        settingsRoles.forEach(function(role) {
                            $('#roleList').append('<span class="role-list-item">' + role.role_name + '</span>');
                        });

                        // Populate the role select options (exclude already added roles)
                        roles.forEach(function(role) {
                            if (!settingsRoles.some(r => r.role_id === role.role_id)) {
                                $('#roleSelect').append('<option value="' + role.role_id + '">' + role.role_name + '</option>');
                            }
                        });

                        // Open the modal popup
                        $('#settingsDetailModal').modal('show');
                    },
                    error: function() {
                        alert('Failed to retrieve settings detail.');
                    }
                });
            });
        });
    </script>
</body>
</html>
