<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
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
    </body>
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
                    '<button class="btn btn-primary">Edit</button>  <button class="btn btn-info">View</button>'
                ]).draw(false);
            });
        });
    </script>
</html>
