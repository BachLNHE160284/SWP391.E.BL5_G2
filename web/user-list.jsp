<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            .modal-body {
                padding: 20px;
            }

            .modal-body img {
                margin-bottom: 15px;
            }

            .user-info-label {
                font-weight: bold;
                color: #495057;
            }

            .user-info-value {
                color: #343a40;
                font-size: 1rem;
            }

            .modal-body .row {
                margin-bottom: 10px;
            }

            .modal-body .col-6 {
                padding-right: 15px;
                padding-left: 15px;
            }

            .user-info-container {
                border-bottom: 1px solid #dee2e6;
                padding-bottom: 10px;
                margin-bottom: 15px;
            }

            .status-icon {
                font-size: 17px;
                margin: 0 10px;
            }
            .active {
                color: green;
            }
            .inactive {
                color: red;
            }

            .container {
                padding: 20px;
            }
            .add-user-button {
                margin-top: 10px;
                text-align: left;
            }

            /* Example CSS to style pagination buttons */
            .dataTables_wrapper .dataTables_paginate .paginate_button {
                padding: 0.5rem 1rem;
                margin: 0 0.1rem;
                border-radius: 0.25rem;
                border: 1px solid #dee2e6;
                background-color: #f8f9fa;
                color: #495057;
                font-size: 0.875rem;
            }

            .dataTables_wrapper .dataTables_paginate .paginate_button.current {
                background-color: #007bff;
                color: #fff;
                border: 1px solid #007bff;
            }

            .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
                background-color: #e9ecef;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <input type="hidden" value='${users}' id="users"/>
            <table id="usersTable" class="table table-striped" style='padding : 10px'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Gender</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Rows will be added dynamically -->
                </tbody>
            </table>
            <div class="add-user-button">
                <button class="btn btn-success">Add new user</button>
            </div>
        </div>

        <div class="container">
            <!-- User Info Modal -->
            <div class="modal fade" id="userInfoModal" tabindex="-1" aria-labelledby="userInfoModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="userInfoModalLabel">User Information</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="text-center user-info-container">
                                <img src="" id="userAvatar" class="rounded-circle" width="100" height="100" alt="Avatar">
                                <h5 class="text-center mt-2" id="userFullName"></h5>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <p class="user-info-label">Gender:</p>
                                    <p class="user-info-value" id="userGender"></p>
                                </div>
                                <div class="col-6">
                                    <p class="user-info-label">Email:</p>
                                    <p class="user-info-value" id="userEmail"></p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <p class="user-info-label">Phone:</p>
                                    <p class="user-info-value" id="userPhone"></p>
                                </div>
                                <div class="col-6">
                                    <p class="user-info-label">Role:</p>
                                    <p class="user-info-value" id="userRole"></p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <p class="user-info-label">Status:</p>
                                    <p class="user-info-value" id="userStatus"></p>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                const users = JSON.parse(document.getElementById("users").value);
                console.log(users);
                // Initialize DataTable
                const table = $('#usersTable').DataTable({
                    "paging": true, // Enable pagination
                    "ordering": true, // Enable sorting
                    "searching": true, // Enable search
                    "responsive": true,
                    "pagingType": "full_numbers", // Use full_numbers for pagination
                    "language": {
                        "paginate": {
                            "first": "First",
                            "last": "Last",
                            "next": ">>",
                            "previous": "<<"
                        }
                    }
                });

                users.forEach(function (user) {
                    table.row.add([
                        user.user_id,
                        user.fullname ? user.fullname : 'NONE',
                        user.gender ? 'Male' : 'Female',
                        user.email_address ? user.email_address : 'NONE',
                        user.phone_number ? user.phone_number : 'NONE',
                        user.role ? user.role.role_name : 'NONE',
                        user.status ? (user.status == 1 ? ' <span class="status-icon active"><i class="fas fa-check-circle"></i> Active</span>' : '<span class="status-icon inactive"><i class="fas fa-times-circle"></i> Inactive</span>') : 'NONE',
                        '<button class="btn btn-primary">Edit</button>  <button class="btn btn-info view-user" data-user-id="' + user.user_id + '">View</button>'
                    ]).draw(false);
                });

                // View button click event to show the modal with user information
                $(document).on('click', '.view-user', function () {
                    const userId = $(this).data('user-id');
                    const user = users.find(user => user.user_id == userId);
                    if (user) {
                        $('#userAvatar').attr('src', user.avatar ? user.avatar : 'default-avatar.png');
                        $('#userFullName').text(user.fullname);
                        $('#userGender').text(user.gender ? 'Male' : 'Female');
                        $('#userEmail').text(user.email_address);
                        $('#userPhone').text(user.phone_number);
                        $('#userRole').text(user.role ? user.role.role_name : 'NONE');
                        $('#userStatus').html(user.status == 1 ? '<span class="status-icon active"><i class="fas fa-check-circle"></i> Active</span>' : '<span class="status-icon inactive"><i class="fas fa-times-circle"></i> Inactive</span>');

                        $('#userInfoModal').modal('show');
                    }
                });
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    </body>
</html>
