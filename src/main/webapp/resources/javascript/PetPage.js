let $tableSize;

$(function () {
    $tableSize = $("tbody tr").length;

    $('[data-toggle="tooltip"]').tooltip({
        container: 'body'
    });

    // Search bar on input listener
    $("#searchFilter").on("input", searchOperations);
});

/**
 * Credit to @Ryan for the search function
 */
// Function to search the operations
function searchOperations() {
    let searchText = $("#searchFilter").val().toLowerCase(); // User-inputted search text

    let name; // Field name to compare

    // Loops each row of the table <tr>
    for (let i = 1; i <= $tableSize; i++) {
        let found = false;

        // Loops each column of the table <td>
        // Starting at 2 because td(1) is the Update button
        for (let j = 1; j <= $("tbody tr:nth-child(" + i + ") td").length; j++) {
            name = $("tbody tr:nth-child(" + i + ") td:eq(" + j + ")").text().toLowerCase();
            if (name.search(searchText) !== -1) {
                found = true;
                break;
            }
        }

        // Search Employee Name to find any occurrence of the Search Input
        // Show them if found
        if (found) {
            if (!$("tbody tr:nth-child(" + i + ")").is(":visible")) {
                $("tbody tr:nth-child(" + i + ")").show();
            }
        }
        // Hide them if not found
        else {
            if ($("tbody tr:nth-child(" + i + ")").is(":visible")) {
                $("tbody tr:nth-child(" + i + ")").hide();
            }
        }
    }
}
