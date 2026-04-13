# Cross-Border E-Commerce Collaboration Platform

**INFO 5100: Application Engineering & Development | Final Project | Group 07**

**Team:** Jianting Dong · Yiru Chen · Jiachuan Li

A Java Swing desktop application simulating a multi-enterprise cross-border e-commerce ecosystem. Three enterprises — Supplier, Logistics, and Platform — collaborate through a shared information system to fulfill international orders.

## Requirements Compliance

| Requirement | Count | Details |
|---|---|---|
| Network | 1 | Global E-Commerce Network |
| Enterprises | 3 | Supplier (China), Platform (USA), Logistics (USA) |
| Organizations | 6 | 2 per enterprise (see below) |
| Roles (excl. admins) | 8 | Supplier Manager, Supplier Staff, Logistics Coordinator, Delivery Staff, Platform Manager, Customer Service, Data Analyst, Customer |
| Work Requests | 6 | 2 cross-org + 4 cross-enterprise |

**Organizations:**
- Supplier: Product Management Org, Order Processing Org
- Platform: Order Management Org, Customer Service Org
- Logistics: Shipment Org, Delivery Org

**Work Requests:**

| # | Type | Flow | Category |
|---|---|---|---|
| 1 | ProcurementRequest | Platform → Supplier | Cross-enterprise |
| 2 | RestockRequest | Platform → Supplier | Cross-enterprise |
| 3 | ShipmentRequest | Supplier → Logistics | Cross-enterprise |
| 4 | ReturnRequest | Platform → Supplier | Cross-enterprise |
| 5 | FulfillmentAssignment | Product Mgmt Org → Order Processing Org (within Supplier) | Cross-organization |
| 6 | DeliveryAssignment | Shipment Org → Delivery Org (within Logistics) | Cross-organization |

## Architecture

```
src/
├── business/                    # Domain model layer
│   ├── network/                 # Network (top-level container)
│   ├── enterprise/              # Enterprise, SupplierEnterprise, PlatformEnterprise, LogisticsEnterprise
│   ├── organization/            # Organization
│   ├── user/                    # Person, UserAccount, UserAccountDirectory
│   ├── product/                 # Product, ProductDirectory
│   ├── order/                   # Order, OrderItem, OrderDirectory
│   ├── shipment/                # Shipment, ShipmentDirectory
│   ├── workrequest/             # WorkRequest + 6 concrete subtypes
│   ├── ConfigureSystem.java     # Bootstrap: seeds all test data
│   └── FakerDataGenerator.java  # Generates random products & orders via JavaFaker
│
└── ui/                          # Swing UI layer
    ├── main/                    # LoginJFrame, Admin/Platform/Supplier dashboards, user mgmt, Customer, Data Analyst
    ├── order/                   # Order CRUD, detail view, customer service, returns
    ├── product/                 # Product CRUD
    ├── logistics/               # Shipment management, tracking, delivery assignment, delay alerts
    ├── report/                  # 3-tab report viewer, supplier KPI
    └── request/                 # Work request processing
```

## Prerequisites

- **JDK 19+** (tested with OpenJDK 21)
- **JavaFaker 1.0.2** and its dependencies

## How to Run

```bash
# 1. Install JDK (macOS)
brew install openjdk@21
export PATH="/usr/local/opt/openjdk@21/bin:$PATH"

# 2. Download dependencies
mkdir -p lib && cd lib
curl -sLO https://repo1.maven.org/maven2/com/github/javafaker/javafaker/1.0.2/javafaker-1.0.2.jar
curl -sLO https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.5/commons-lang3-3.5.jar
curl -sLO https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.23/snakeyaml-1.23.jar
curl -sLO https://repo1.maven.org/maven2/com/github/mifmif/generex/1.0.2/generex-1.0.2.jar
curl -sLO https://repo1.maven.org/maven2/dk/brics/automaton/automaton/1.11-8/automaton-1.11-8.jar
cd ..

# 3. Compile & Run
javac -cp "lib/*" -d build/classes $(find src -name "*.java")
java -cp "build/classes:lib/*" ui.main.LoginJFrame
```

Alternatively, open the project in **NetBeans** (File → Open Project) and run directly.

## Login Credentials

| Username | Password | Role | Dashboard |
|---|---|---|---|
| `admin` | `123` | System Admin | Full access: users, products, orders, shipments, reports |
| `platform_mgr` | `123` | Platform Manager | Orders, reports, customer service, returns |
| `customer_service` | `123` | Customer Service | Order list with resolve ticket |
| `supplier` | `Supplier@123` | Supplier Manager | Products, work requests, supplier KPI |
| `supplier_staff` | `123` | Supplier Staff | Products, work requests (same as Supplier Manager) |
| `logistics1` | `123` | Logistics Coordinator | Shipment management, tracking, assign delivery, delay alerts |
| `delivery1` | `123` | Delivery Staff | View assigned shipments, mark delivered/failed |
| `delivery2` | `123` | Delivery Staff | Same as above |
| `analyst` | `123` | Data Analyst | Order summary, country summary, KPI dashboard |
| `customer` | `123` | Customer | Place orders, request returns |

## Key Features

- **Form validation** — Email regex, name length (2–50 chars), username (3+ chars, alphanumeric), password strength (min 6, letter + digit)
- **Role-based authentication** — 8 non-admin roles + 1 admin, each routed to dedicated dashboards
- **Product management** — Full CRUD with validation, restock, low-stock detection
- **Order lifecycle** — Create → Shipped → Delivered → Returned, with cancel support
- **Smart risk detection** — Auto-flags orders >$1,000 (High Value) and unshipped >3 days (Delay Risk)
- **6 work request types** — Cross-enterprise (Procurement, Restock, Shipment, Return) + Cross-org (Fulfillment Assignment, Delivery Assignment)
- **Shipment management** — CRUD, tracking, delivery staff assignment, delay alerts
- **Reporting** — 3-tab report (order summary, shipment summary, country breakdown) + supplier KPI + data analyst dashboard
- **Customer portal** — Place orders and request returns
- **Faker data seeding** — 28 products, 10 orders, 10 shipments, 5 work requests generated at startup

## Submission Documents

| Document | File |
|---|---|
| UML Class Diagram | `domain_model.pdf` |
| UI Navigation Diagram | `ui_navigation.pdf` |
| Component Diagram | `component_diagram.pdf` |

## Team Contributions

| Member | Module | Key Classes |
|---|---|---|
| Jianting Dong | Platform + Orders + Reports + Admin + Shared Foundation | LoginJFrame, AdminWorkAreaJPanel, PlatformWorkAreaJPanel, OrderJPanel, ReportViewerJPanel, SystemOverviewJPanel, ManageUsersJPanel, ConfigureSystem, FakerDataGenerator, DataAnalystJPanel, CustomerJPanel |
| Yiru Chen | Supplier + Products + Work Requests | SupplierWorkAreaJPanel, ManageProductJPanel, SupplierWorkRequestJPanel, SupplierReportJPanel, WorkRequest, ProcurementRequest, RestockRequest, FulfillmentAssignment, ReturnRequest |
| Jiachuan Li | Logistics + Shipments + Delivery | LogisticsWorkAreaJPanel, ManageShipmentsJPanel, TrackShipmentsJPanel, AssignDeliveryJPanel, DelayAlertsJPanel, DeliveryJPanel, Shipment, ShipmentDirectory, LogisticsEnterprise, ShipmentRequest, DeliveryAssignment |
