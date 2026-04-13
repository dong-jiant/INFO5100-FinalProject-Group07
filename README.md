# Cross-Border E-Commerce Collaboration Platform

**INFO 5100: Application Engineering & Development | Final Project | Group 07**

**Team:** Jianting Dong · Yiru Chen · Jiachuan Li

A Java Swing desktop application simulating a multi-enterprise cross-border e-commerce ecosystem. Three enterprises — Supplier, Logistics, and Platform — collaborate through a shared information system to fulfill international orders.

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
│   ├── workrequest/             # WorkRequest, ProcurementRequest, RestockRequest, ShipmentRequest
│   ├── ConfigureSystem.java     # Bootstrap: seeds all test data
│   └── FakerDataGenerator.java  # Generates random products & orders via JavaFaker
│
└── ui/                          # Swing UI layer
    ├── main/                    # LoginJFrame, Admin/Platform/Supplier dashboards, user management
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
| `admin` | `123` | System Admin | Full access: users, products, orders, shipments, reports, system overview |
| `platform_mgr` | `123` | Platform Manager | Orders, reports, customer service, returns |
| `customer_service` | `123` | Customer Service | Order list with resolve ticket |
| `supplier` | `Supplier@123` | Supplier Manager | Products, work requests, supplier KPI |
| `logistics1` | `123` | Logistics Coordinator | Shipment management, tracking, assign delivery, delay alerts |
| `delivery1` | `123` | Delivery Staff | View assigned shipments, mark delivered/failed |
| `delivery2` | `123` | Delivery Staff | Same as above |

## Key Features

- **Role-based authentication** — 6 roles route to dedicated dashboards
- **Product management** — Full CRUD with validation, restock, low-stock detection
- **Order lifecycle** — Create → Shipped → Delivered → Returned, with cancel support
- **Smart risk detection** — Auto-flags orders >$1,000 (High Value) and unshipped >3 days (Delay Risk)
- **Cross-enterprise work requests** — Procurement and restock requests between Platform and Supplier
- **Shipment management** — CRUD, tracking, delivery staff assignment, delay alerts
- **Reporting** — 3-tab report (order summary, shipment summary, country breakdown) + supplier KPI
- **Faker data seeding** — 28 products and 10 orders with realistic data generated at startup

## Design Patterns

- **Layered architecture** — `business/` (model) and `ui/` (view + controller) separation
- **Directory pattern** — `ProductDirectory`, `OrderDirectory`, `ShipmentDirectory`, etc. as in-memory collection managers
- **Inheritance** — `Enterprise` → 3 subtypes, `WorkRequest` → 3 subtypes
- **CardLayout navigation** — Dashboards swap sub-panels via `CardLayout`
- **Role-based access control** — Login dispatches to role-specific work areas

## Team Contributions

| Member | Module | Key Classes |
|---|---|---|
| Jianting Dong | Platform + Orders + Reports + Admin + Shared Foundation | LoginJFrame, AdminWorkAreaJPanel, PlatformWorkAreaJPanel, OrderJPanel, ReportViewerJPanel, SystemOverviewJPanel, ManageUsersJPanel, ConfigureSystem, FakerDataGenerator |
| Yiru Chen | Supplier + Products + Work Requests | SupplierWorkAreaJPanel, ManageProductJPanel, SupplierWorkRequestJPanel, SupplierReportJPanel, WorkRequest, ProcurementRequest, RestockRequest |
| Jiachuan Li | Logistics + Shipments + Delivery | LogisticsWorkAreaJPanel, ManageShipmentsJPanel, TrackShipmentsJPanel, AssignDeliveryJPanel, DelayAlertsJPanel, DeliveryJPanel, Shipment, ShipmentDirectory, LogisticsEnterprise |
