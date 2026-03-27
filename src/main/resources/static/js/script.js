let selected = { group: "" };

function selectOption(el, type, value) {
    let cards = el.parentElement.children;
    for (const element of cards) {
        element.classList.remove("selected");
    }
    el.classList.add("selected");
    selected[type] = value;
}

async function generateTrip() {
    let days = Number.parseInt(document.getElementById("days").value);
    let budget = Number.parseFloat(document.getElementById("budget").value);
    let group = selected.group;

    let result = document.getElementById("result");
    result.innerHTML = "";

    if (!days || !budget || !group) {
        result.innerHTML = "Fill all details";
        return;
    }

    try {
        const response = await fetch(
            `http://127.0.0.1:8080/api/travel-plan?days=${days}&budget=${budget}`
        );

        if (!response.ok) {
            throw new Error("Backend error");
        }

        const data = await response.json();

        if (data.message) {
            result.innerHTML = data.message;
            return;
        }

        const perDay = {
            hotel: (data.hotel / days).toFixed(2),
            food: (data.food / days).toFixed(2),
            travel: (data.travel / days).toFixed(2),
            activities: (data.activities / days).toFixed(2)
        };

        result.innerHTML = `
        <div class="place">
            <h3>Recommended Destination</h3>
            <p>${data.destination} (${data.destinationPrice})</p>
        </div>

        <div class="place">
            <h3>Hotel</h3>
            <p>Total: ${data.hotelTotal} | Per Day: ${data.hotelPerDay.toFixed(2)}</p>
        </div>

        <div class="place">
            <h3>Food</h3>
            <p>Total: ${data.foodTotal} | Per Day: ${data.foodPerDay.toFixed(2)}</p>
        </div>

        <div class="place">
            <h3>Travel</h3>
            <p>Total: ${data.travelTotal} | Per Day: ${data.travelPerDay.toFixed(2)}</p>
        </div>

        <div class="place">
            <h3>Activities</h3>
            <p>Total: ${data.activitiesTotal} | Per Day: ${data.activitiesPerDay.toFixed(2)}</p>
        </div>
    `;
    } catch (err) {
        console.error(err);
        result.innerHTML = "Failed to connect to backend";
    }
}