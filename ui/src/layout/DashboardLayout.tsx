import { AppShell } from "@mantine/core";
import { Outlet } from "react-router-dom";
import { Header } from "../components/header";

export function DashboardLayout() {
  return (
    <AppShell
      header={{ height: 60 }}
      navbar={{ width: 250, breakpoint: "sm" }}
      padding="md"
    >
      <AppShell.Header>
        <Header />
      </AppShell.Header>

      <AppShell.Main>
        <Outlet />
      </AppShell.Main>
    </AppShell>
  );
}
