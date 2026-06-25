import { IconSearch } from "@tabler/icons-react";
import {
  Autocomplete,
  Burger,
  Divider,
  Drawer,
  Group,
  ScrollArea,
} from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import classes from "./header.module.css";
import { ColorToggleButton } from "../buttons/color-toggle";
import { GithubLinkButton } from "../buttons/github-link";

const links = [
  { link: "/about", label: "Features" },
  { link: "/community", label: "Community" },
];

export function Header() {
  const [opened, { toggle, close }] = useDisclosure(false);

  const items = links.map((link) => (
    <a
      key={link.label}
      href={link.link}
      className={classes.link}
      onClick={(event) => event.preventDefault()}
    >
      {link.label}
    </a>
  ));

  return (
    <header className={classes.header}>
      <div className={classes.inner}>
        <Group>
          <Burger
            opened={opened}
            onClick={toggle}
            size="sm"
            hiddenFrom="sm"
            aria-label="Toggle navigation"
          />
          <p>Logo</p>
          <p>What's up</p>
        </Group>

        <Group>
          <Group ml={50} gap={5} className={classes.links} visibleFrom="sm">
            {items}
          </Group>
          <Autocomplete
            className={classes.search}
            placeholder="Search"
            leftSection={<IconSearch size={16} stroke={1.5} />}
            data={[
              "React",
              "Angular",
              "Vue",
              "Next.js",
              "Riot.js",
              "Svelte",
              "Blitz.js",
            ]}
            visibleFrom="xs"
          />
          <GithubLinkButton />
          <ColorToggleButton />
        </Group>
      </div>

      <Drawer
        opened={opened}
        onClose={close}
        size="100%"
        padding="md"
        title="Navigation"
        hiddenFrom="sm"
        zIndex={1000000}
      >
        <ScrollArea h="calc(100vh - 80px" mx="-md">
          <Divider my="sm" />
          <Autocomplete
            placeholder="Search"
            leftSection={<IconSearch size={16} stroke={1.5} />}
            // make dynamic later
            data={[
              "React",
              "Angular",
              "Vue",
              "Next.js",
              "Riot.js",
              "Svelte",
              "Blitz.js",
            ]}
            mx="md"
            mb="sm"
          />
          {items}
        </ScrollArea>
      </Drawer>
    </header>
  );
}
