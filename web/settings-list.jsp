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
                cursor: pointer;
            }
            .delete-role-icon {
                margin-left: 10px;
                color: red;
                cursor: pointer;
            }
            .disabled {
                pointer-events: none;
                opacity: 0.6;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Settings List</h1>

            <!-- Hidden input to store the JSON data -->
            <input type="hidden" id="settingsData" value='${settings}'>
            <input type="hidden" id="roles" value='${roles}'>

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
                <tr>
                    <td colspan="3"><button class="btn btn-primary" id="add-new-settings">Add new Settings</button></td>
                </tr>
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
                        <!-- Success and Failure messages -->
                        <div id="successMessage" class="alert alert-success" style="display:none;">Role deleted successfully!</div>
                        <div id="errorMessage" class="alert alert-danger" style="display:none;">Failed to delete the role.</div>
                        <div id="addSuccessMessage" class="alert alert-success" style="display:none;">Role added successfully!</div>
                        <div id="addErrorMessage" class="alert alert-danger" style="display:none;">Failed to add the role.</div>
                        <div class="form-group">
                            <label>Url:</label>
                            <div id="url"></div>
                        </div>
                        <div class="form-group">
                            <label>Pre-added Roles:</label>
                            <div id="roleList"></div>
                        </div>
                        <div class="form-group" id="group-select-role">
                            <label>Add New Role:</label>
                            <select id="roleSelect" class="form-control"></select>
                            <button class="btn btn-primary" id="btn-add-role-to-url">Add</button>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Popup Modal for Adding New Settings -->
        <div class="modal fade" id="addSettingsModal" tabindex="-1" aria-labelledby="addSettingsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addSettingsModalLabel">Add New Settings</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Success and Failure messages -->
                        <div id="addSuccessMessage2" class="alert alert-success" style="display:none;">Settings added successfully!</div>
                        <div id="addErrorMessage2" class="alert alert-danger" style="display:none;">Failed to add settings.</div>

                        <div class="form-group">
                            <label>URL:</label>
                            <input type="text" id="newSettingsURL" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Select Role:</label>
                            <select id="newRoleSelect" class="form-control" required>
                                <!-- Populate this dynamically with roles -->
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" id="submitNewSettings" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </div>
        </div>



        <!-- jQuery and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            var urlDetail = "";
            var roles = [];
            $(document).ready(function () {
                // Get the JSON data from the hidden input
                var settingsData = JSON.parse($('#settingsData').val());
                roles = JSON.parse($('#roles').val());

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
                        {data: 'url'}, // URL column
                        {
                            data: null,
                            render: function (data, type, row, meta) {
                                return '<button class="btn btn-primary view-details" data-url="' + row.url + '">View Details</button>';
                            }
                        }
                    ]
                });

                // Handle "View Details" button click
                $('#settingsTable tbody').on('click', '.view-details', function () {
                    var url = $(this).data('url');
                    urlDetail = url;
                    $('#url').empty();
                    $('#url').append(url);
                    // Send AJAX request to get settings detail
                    $.ajax({
                        url: 'settings-detail',
                        method: 'GET',
                        data: {url: url},
                        success: function (response) {
                            var settingsDetail = JSON.parse(response);
                            var settingsRoles = settingsDetail.roles;
                            // Clear previous role list and role select options
                            $('#roleList').empty();
                            $('#roleSelect').empty();

                            // Populate the pre-added roles list with Bootstrap-styled badges and delete icons
                            settingsRoles.forEach(function (role) {
                                $('#roleList').append('<span class="role-list-item" data-role-id="' + role.role_id + '" data-url="' + url + '" data-role-name="' + role.role_name + '">' + role.role_name + '<span class="delete-role-icon">🗑️</span></span>');
                            });

                            // Populate the role select options (exclude already added roles)
                            var countLength = 0;
                            roles.forEach(function (role) {
                                if (!settingsRoles.some(r => r.role_id === role.role_id)) {
                                    $('#roleSelect').append('<option value="' + role.role_id + '">' + role.role_name + '</option>');
                                } else {
                                    countLength++;
                                }
                            });

                            if (roles.length === countLength) {
                                $("#group-select-role").html('');
                            }

                            // Open the modal popup
                            $('#settingsDetailModal').modal('show');
                        },
                        error: function () {
                            alert('Failed to retrieve settings detail.');
                        }
                    });
                });

                $('#group-select-role').on('click', '#btn-add-role-to-url', function () {
                    var roleId = $('#roleSelect').val();
                    var roleName = $('#roleSelect option:selected').text();
                    console.log(roleName + '  ' + roleId);
                    $.ajax({
                        url: 'add-role-to-url',
                        method: 'POST',
                        data: {roleId: roleId, url: urlDetail},
                        success: function (response) {
                            $('#addSuccessMessage').show().delay(2000).fadeOut();
                            // Remove the option
                            $('#roleSelect option[value="' + roleId + '"]').remove();
                            // check if select box has 0 options --> remove group-select-role
                            if ($('#roleSelect option').length === 0)
                                $('#group-select-role').html('');

                            // Add to the preselected role
                            $('#roleList').append('<span class="role-list-item" data-role-id="' + roleId + '" data-url="' + urlDetail + '" data-role-name="' + roleName + '">' + roleName + '<span class="delete-role-icon">🗑️</span></span>');
                        },
                        error: function (error) {
                            
                            $('#addErrorMessage').show().delay(2000).fadeOut();
                        },
                        complete: function () {
                            // Enable all role items
                            $('#roleList .role-list-item').removeClass('disabled');
                        }
                    });
                });

                // Handle Add New Settings Button click
                $('#add-new-settings').on('click', function () {
                    // Clear the input fields and previous messages
                    $('#newSettingsURL').val('');
                    $('#newRoleSelect').empty();
                    $('#addSuccessMessage').hide();
                    $('#addErrorMessage').hide();
                    roles.forEach(function (role) {
                        $('#newRoleSelect').append('<option value="' + role.role_id + '">' + role.role_name + '</option>');
                    });

                    // Show the modal
                    $('#addSettingsModal').modal('show');
                });

                // Handle the form submission for adding new settings
                $('#submitNewSettings').on('click', function () {
                    var newUrl = $('#newSettingsURL').val();
                    var newRoleId = $('#newRoleSelect').val();
                    var newRoleName = $('#newRoleSelect option:selected').text();

                    // Validate URL is not empty
                    if (!newUrl) {
                        alert('URL cannot be empty.');
                        return;
                    }

                    // Disable the submit button to prevent multiple submissions
                    $('#submitNewSettings').prop('disabled', true);

                    // Send AJAX request to add new settings
                    $.ajax({
                        url: 'add-role-to-url',
                        method: 'POST',
                        data: {roleId: newRoleId, url: newUrl},
                        success: function (response) {
                            $('#addSuccessMessage2').show().delay(2000).fadeOut();
                            // Hide the modal
                            $('#addSettingsModal').modal('hide');
                            location.reload();
                        },
                        error: function (error) {
                            $('#addErrorMessage2').text(error.responseText);
                            $('#addErrorMessage2').show().delay(2000).fadeOut();
                        },
                        complete: function () {
                            // Enable the submit button
                            $('#submitNewSettings').prop('disabled', false);
                        }
                    });
                });


                // Handle role delete action
                $('#roleList').on('click', '.delete-role-icon', function () {
                    var roleId = $(this).closest('.role-list-item').data('role-id');
                    var roleName = $(this).closest('.role-list-item').data('role-name');
                    var url = $(this).closest('.role-list-item').data('url');
                    var $roleItem = $(this).closest('.role-list-item');

                    // Disable all role items
                    $('#roleList .role-list-item').addClass('disabled');

                    // Send AJAX request to delete the role
                    $.ajax({
                        url: 'delete-role-from-url',
                        method: 'POST',
                        data: {roleId: roleId, url: url},
                        success: function (response) {
                            $('#successMessage').show().delay(2000).fadeOut();
                            $roleItem.remove(); // Remove the role item from the DOM
                            if ($('#group-select-role').text().length <= 0) {
                                $('#group-select-role').append('<label>Add New Role:</label>');
                                $('#group-select-role').append('<select id="roleSelect" class="form-control"></select>');
                                $('#group-select-role').append('<button class="btn btn-primary" id="btn-add-role-to-url">Add</button>');
                            }
                            // Add the deleted role back to the select options
                            $('#roleSelect').append('<option value="' + roleId + '">' + roleName + '</option>');
                        },
                        error: function () {
                            $('#errorMessage').show().delay(2000).fadeOut();
                        },
                        complete: function () {
                            // Enable all role items
                            $('#roleList .role-list-item').removeClass('disabled');
                        }
                    });
                });
            });
        </script>
    </body>
</html>
