# SpringBoot-L1

EndPoints:

Customer Controller:

| RequestType | REST API | DESCRIPTION |
| --- | --- | --- |
| GET | /customers | getAllCustomers() |
| POST | /customer | createCustomer() |
| PUT | /customers/id | updateCustomer() |
| DELETE | /customers/id | deleteCustomer() |

Account Controller:

| RequestType | REST API | DESCRIPTION |
| --- | --- | --- |
| GET | /customers/id/accounts | getAllAccountsByCustomerId() |
| POST | /customer/id/accounts | createAccount() |
| PUT | /customer/id/accounts/id | updateAccount() |
| DELETE | /customers/id/accounts/id | deleteAccount() |
| PUT | /transfer | transferMoney() |

