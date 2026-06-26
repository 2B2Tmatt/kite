import "@mantine/core/styles.css";
import { MantineProvider } from "@mantine/core";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { DashboardLayout } from "./layout/DashboardLayout";
import Homepage from "./pages/homepage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Homepage />,
  },
  {
    path: "/monitors",
    element: <DashboardLayout />,
    children: [
      {
        index: true,
        element: <Homepage />,
      },
    ],
  },
]);

function App() {
  return (
    <MantineProvider>
      <RouterProvider router={router} />
    </MantineProvider>
  );
}

export default App;
