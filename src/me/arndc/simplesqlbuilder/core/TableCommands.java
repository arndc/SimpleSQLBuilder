package me.arndc.simplesqlbuilder.core;

/**
 * This interface describes the commands that can be executed on a table.
 *
 * @see Table
 */
interface TableCommands {
    String createStatement();

    String dropStatement();
}
