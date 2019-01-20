using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace SnappyPhone.Migrations
{
    public partial class UpdatePhones : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "ArtDating",
                table: "Products");

            migrationBuilder.DropColumn(
                name: "ArtDescription",
                table: "Products");

            migrationBuilder.DropColumn(
                name: "ArtistBirthDate",
                table: "Products");

            migrationBuilder.DropColumn(
                name: "ArtistDeathDate",
                table: "Products");

            migrationBuilder.RenameColumn(
                name: "Title",
                table: "Products",
                newName: "Subcategory");

            migrationBuilder.RenameColumn(
                name: "Size",
                table: "Products",
                newName: "PictureId");

            migrationBuilder.RenameColumn(
                name: "ArtistNationality",
                table: "Products",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "Artist",
                table: "Products",
                newName: "Description");

            migrationBuilder.RenameColumn(
                name: "ArtId",
                table: "Products",
                newName: "Color");

            migrationBuilder.UpdateData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1,
                column: "OrderDate",
                value: new DateTime(2018, 12, 9, 12, 51, 10, 498, DateTimeKind.Utc));
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "Subcategory",
                table: "Products",
                newName: "Title");

            migrationBuilder.RenameColumn(
                name: "PictureId",
                table: "Products",
                newName: "Size");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "Products",
                newName: "ArtistNationality");

            migrationBuilder.RenameColumn(
                name: "Description",
                table: "Products",
                newName: "Artist");

            migrationBuilder.RenameColumn(
                name: "Color",
                table: "Products",
                newName: "ArtId");

            migrationBuilder.AddColumn<string>(
                name: "ArtDating",
                table: "Products",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "ArtDescription",
                table: "Products",
                nullable: true);

            migrationBuilder.AddColumn<DateTime>(
                name: "ArtistBirthDate",
                table: "Products",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<DateTime>(
                name: "ArtistDeathDate",
                table: "Products",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.UpdateData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1,
                column: "OrderDate",
                value: new DateTime(2018, 11, 24, 15, 9, 50, 684, DateTimeKind.Utc));
        }
    }
}
