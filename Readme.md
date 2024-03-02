### Book My Ticket:
We want to design an online movie ticket booking portal where people from different cities can book 
for movie tickets online. Booking portal has capabilities to connect with theaters and manages 
movies shows, running slots and seat availability.
https://lucid.app/lucidchart/149cfdbc-4cec-4644-8e4d-765ade355577/edit?viewport_loc=-282%2C-185%2C1971%2C880%2C0_0&invitationId=inv_3f760d05-3579-4165-ab4f-bd49ddc996b6

### Requirements:
-User can select for city, theatre and check running movie shows
-User can choose multiple available seats and book those seats.
-System should be highly concurrent and able to handle multiple booking requests at same time.
-System should not allow the same seat allocation to multiple users.
### Expectations:
-interested in low level object-oriented design skills. Workable code is super important.
-System should be able to scale easily for large user-base.
-REST APIs are preferred but feel free to have any.
-Mocking external dependencies or using similar in-memory solutions is absolutely fine.

### Assumptions:
- I have some dummy data placed in the database already
- Assume all Users are registered, authenticated, and logged in to the Application.
- I have picked sql db - as I have a defined schema for this application
  in that there are 4 tables which are interacting with each other
  so sql makes complex queries easy to handle, so I chose sql.

### Enhancements:
* Seat type: Silver, Gold, Diamond and define different prices for it
seat table I can put this type or in code on the basis of letter
suppose my row containing A can be gold, letters from b-f can be diamond and so on
when get seats is called I can give user on the bases if type and show different prices for it.

* We can add payment flow and implement different payment modes.
payment table so when user creates booking we have boking id and booking id is liked with
payment payment call create entry in payment with pending
if success mark payment entry succes then mark booking id confirmed and related show_id seats isBooked true.
else fail payment failed booking failed lock open hojaega in its desired time.

* Checking if show creation is allowed.
theatre_id screen_name - get all enteries
assume no is overlapping currently 
we know start time and duration of all the shoes - startTime endTime

1pm 3pm
1pm 5pm
11:50pm 1:50pm
2pm 4pm, 5pm 7pm, 8pm 10pm

startTime <st && endtime<st || startTime>et && endTime>et
1<2 && 3<2 || 1>4 && 3>4
return false; break;
1<5 && 3<5 || 1>7 && 3>7
1<2 && 4:30<2 || 1>4 && 4:30>4







