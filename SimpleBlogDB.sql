USE [master]
GO
/****** Object:  Database [SimpleBlog]    Script Date: 4/11/2020 5:44:03 PM ******/
CREATE DATABASE [SimpleBlog]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SimpleBlog', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\SimpleBlog.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SimpleBlog_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\SimpleBlog_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [SimpleBlog] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SimpleBlog].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SimpleBlog] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SimpleBlog] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SimpleBlog] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SimpleBlog] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SimpleBlog] SET ARITHABORT OFF 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SimpleBlog] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SimpleBlog] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SimpleBlog] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SimpleBlog] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SimpleBlog] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SimpleBlog] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SimpleBlog] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SimpleBlog] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SimpleBlog] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SimpleBlog] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SimpleBlog] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SimpleBlog] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SimpleBlog] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SimpleBlog] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SimpleBlog] SET  MULTI_USER 
GO
ALTER DATABASE [SimpleBlog] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SimpleBlog] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SimpleBlog] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SimpleBlog] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SimpleBlog] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SimpleBlog] SET QUERY_STORE = OFF
GO
USE [SimpleBlog]
GO
/****** Object:  Table [dbo].[tbl_Article]    Script Date: 4/11/2020 5:44:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Article](
	[ArticleID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](50) NULL,
	[ShortDescription] [nvarchar](100) NULL,
	[ArticleContent] [nvarchar](500) NULL,
	[Author] [nvarchar](50) NULL,
	[TimeGenerated] [datetime] NULL,
	[StatusID] [int] NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Articles] PRIMARY KEY CLUSTERED 
(
	[ArticleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Comment]    Script Date: 4/11/2020 5:44:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[CommentContent] [nvarchar](200) NULL,
	[Author] [nvarchar](50) NULL,
	[TimeGenerated] [datetime] NULL,
	[ArticleID] [int] NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Comments] PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 4/11/2020 5:44:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Roles] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Status]    Script Date: 4/11/2020 5:44:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Status](
	[StatusID] [int] IDENTITY(1,1) NOT NULL,
	[StatusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Statuses] PRIMARY KEY CLUSTERED 
(
	[StatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 4/11/2020 5:44:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[Email] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Password] [nvarchar](100) NULL,
	[RoleID] [int] NULL,
	[StatusID] [int] NULL,
 CONSTRAINT [PK_tbl_Users] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tbl_Article] ON 

INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (1, N'Buon', N'Hom nay tui buon', N'Hom nay tui buon mot minh tren pho dong noi anh den soi sang lung linh ...', N'Phuong Dat', CAST(N'2020-01-21T21:19:54.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (2, N'Tet', N'Tet sap den roi', N'Con 3 ngay nua la den tet', N'Phuong Dat', CAST(N'2020-01-22T12:48:19.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (3, N'Xuan ve', N'Mua xuan lai ve', N'Mua xuan ve cho cay trai dam choi nay loc', N'Phuong Dat', CAST(N'2020-01-22T12:50:17.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (4, N'Da Lat', N'Da Lat that lanh', N'Da Lat nam nay lanh hon nam truoc', N'Phuong Dat', CAST(N'2020-01-22T12:50:50.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (5, N'Giao thua', N'Don giao thua voi ba me', N'Nam nay toi khong di choi, ma o nha don giao thua voi ba me hihi', N'Phuong Dat', CAST(N'2020-01-22T12:52:25.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (6, N'Tham ong ba', N'Toi di tham ong ba', N'Mung 1, toi va ba me den tham ong ba', N'Phuong Dat', CAST(N'2020-01-22T12:53:03.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (7, N'Meow', N'Meow Meow', N'Meow Meow Meow Meow :)', N'Meow', CAST(N'2020-01-22T12:58:31.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (8, N'Sad', N'ahuhu', N':< :< :<', N'Meow', CAST(N'2020-01-22T12:58:49.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (9, N'Happy', N'ahihi', N':> :> :>', N'Meow', CAST(N'2020-01-22T12:59:04.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (10, N'Funny', N'Ahaha', N':)) :)) :))', N'Meow', CAST(N'2020-01-22T12:59:20.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (11, N'Banh chung', N'Canh banh chung', N'Hom nay toi thuc canh banh chung', N'Meow', CAST(N'2020-01-22T13:00:25.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (12, N'Li xi', N'Duoc li xi', N'Hom nay toi duoc li xi', N'Meow', CAST(N'2020-01-22T13:00:42.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (13, N'Gau', N'Gau Gau', N'Gau Gau Gau Gau :>', N'Gau Gau', CAST(N'2020-01-22T13:01:56.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (14, N'Benh', N'Toi bi benh', N'Hom nay toi bi sot cao', N'Gau Gau', CAST(N'2020-01-22T13:02:47.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (15, N'Benh vien', N'Toi di benh vien', N'Toi benh nang nen phai vao vien', N'Gau Gau', CAST(N'2020-01-22T13:03:08.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (16, N'Tham thay co', N'Toi di tham thay co', N'Hom nay toi va cac ban di tham thay co', N'Gau Gau', CAST(N'2020-01-22T13:03:41.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (17, N'Loto', N'Choi loto', N'Hom nay tui choi loto aihih', N'Gau Gau', CAST(N'2020-01-22T13:04:09.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (18, N'Sap di hoc', N'Toi sap di hoc lai', N'Con 10 ngay nua la di hoc lai huhu', N'Gau Gau', CAST(N'2020-01-22T13:04:32.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (19, N'Chip', N'Chip Chip', N'Chip Chip Chip Chip :>', N'Chip Chip', CAST(N'2020-01-22T13:05:20.000' AS DateTime), 2, N'user_03@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (20, N'Con ga', N'Con ga con', N'Di loanh quanh trong san co con ga co con ga', N'Chip Chip', CAST(N'2020-01-22T13:05:46.000' AS DateTime), 2, N'user_03@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (21, N'Vit', N'Con vit', N'Mot con vit xoe ra hai cai canh', N'Chip Chip', CAST(N'2020-01-22T13:06:16.000' AS DateTime), 2, N'user_03@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (22, N'Nham nhi', N'ahihi', N'ahihihihihihihi :>', N'Chip Chip', CAST(N'2020-01-22T13:06:33.000' AS DateTime), 2, N'user_03@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (23, N'Nham nhi x2', N'Ahihi', N'Toi cam thay minh that nham nhi :)))', N'Chip Chip', CAST(N'2020-01-22T13:07:17.000' AS DateTime), 2, N'user_03@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (24, N':>', N'Ahihi', N'Ahihihihihi x 3.14', N'Chip Chip', CAST(N'2020-01-22T13:07:34.000' AS DateTime), 2, N'user_03@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (25, N'Di cho hoa', N'Di xem cho hoa', N'28 Tet, toi di xem cho hoa', N'Phuong Dat', CAST(N'2020-01-22T13:12:07.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (26, N'Di chua', N'Toi di chua', N'Mung 2, toi di chua thap huong', N'Phuong Dat', CAST(N'2020-01-22T13:12:33.000' AS DateTime), 2, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (27, N'Quan ao', N'Quan ao moi', N'Toi duoc mua quan ao moi', N'Meow', CAST(N'2020-01-22T13:14:07.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (28, N'Balo', N'Balo moi', N'Toi duoc tang balo moi hihi', N'Gau Gau', CAST(N'2020-01-22T13:14:47.000' AS DateTime), 2, N'user_02@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (29, N'a1', N'a1', N'a1', N'Meow', CAST(N'2020-01-22T15:20:56.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (30, N'a2', N'a2', N'a2', N'Meow', CAST(N'2020-01-22T15:21:00.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (31, N'a3', N'a3', N'a3', N'Meow', CAST(N'2020-01-22T15:21:05.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (32, N'a4', N'a4', N'a4', N'Meow', CAST(N'2020-01-22T15:21:08.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (33, N'a5', N'a5', N'a5', N'Meow', CAST(N'2020-01-22T15:21:12.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (34, N'a6', N'a6', N'a6', N'Meow', CAST(N'2020-01-22T15:21:16.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (35, N'a7', N'a7', N'a7', N'Meow', CAST(N'2020-01-22T15:21:22.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (36, N'a8', N'a8', N'a8', N'Meow', CAST(N'2020-01-22T15:21:28.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (37, N'a9', N'a9', N'a9', N'Meow', CAST(N'2020-01-22T15:21:35.000' AS DateTime), 3, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (38, N'a10', N'a10', N'a10', N'Meow', CAST(N'2020-01-22T15:21:41.000' AS DateTime), 3, N'user_01@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (39, N'a11', N'a11', N'a11', N'Phuong Dat', CAST(N'2020-01-22T20:18:31.000' AS DateTime), 1, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (40, N'a12', N'a12', N'a12', N'Phuong Dat', CAST(N'2020-01-22T20:18:39.000' AS DateTime), 1, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (41, N'a13', N'a13', N'a13', N'Phuong Dat', CAST(N'2020-01-22T20:18:46.000' AS DateTime), 1, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (42, N'a14', N'a14', N'a14', N'Phuong Dat', CAST(N'2020-01-22T20:18:51.000' AS DateTime), 1, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Article] ([ArticleID], [Title], [ShortDescription], [ArticleContent], [Author], [TimeGenerated], [StatusID], [Email]) VALUES (46, N'Hom nay thu 2', N'thu 2', N'Hom nay thu 2 dau tuan', N'Hoa', CAST(N'2020-02-24T16:50:27.000' AS DateTime), 2, N'HoaDNT@gmail.com')
SET IDENTITY_INSERT [dbo].[tbl_Article] OFF
SET IDENTITY_INSERT [dbo].[tbl_Comment] ON 

INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (1, N'.', N'Meow', CAST(N'2020-01-21T21:21:13.000' AS DateTime), 1, N'user_01@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (2, N'.', N'Gau Gau', CAST(N'2020-01-21T21:22:02.000' AS DateTime), 1, N'user_02@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (3, N'?', N'Phuong Dat', CAST(N'2020-01-22T12:46:59.000' AS DateTime), 1, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (4, N'oh', N'Meow', CAST(N'2020-01-22T12:56:53.000' AS DateTime), 2, N'user_01@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (5, N'<3', N'Meow', CAST(N'2020-01-22T12:57:05.000' AS DateTime), 6, N'user_01@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (6, N'.', N'Meow', CAST(N'2020-01-22T12:57:14.000' AS DateTime), 4, N'user_01@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (7, N'oh', N'Gau Gau', CAST(N'2020-01-22T13:01:09.000' AS DateTime), 6, N'user_02@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (8, N'.', N'Gau Gau', CAST(N'2020-01-22T13:01:18.000' AS DateTime), 5, N'user_02@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (9, N'.', N'Gau Gau', CAST(N'2020-01-22T13:01:24.000' AS DateTime), 4, N'user_02@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (10, N'.', N'Chip Chip', CAST(N'2020-01-22T13:04:53.000' AS DateTime), 5, N'user_03@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (11, N'oh', N'Chip Chip', CAST(N'2020-01-22T13:04:59.000' AS DateTime), 6, N'user_03@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (12, N'why?', N'Phuong Dat', CAST(N'2020-01-22T13:52:35.000' AS DateTime), 8, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (13, N'.', N'Phuong Dat', CAST(N'2020-01-22T13:52:46.000' AS DateTime), 9, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (14, N':<', N'Phuong Dat', CAST(N'2020-01-22T13:53:08.000' AS DateTime), 1, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (18, N'.', N'Phuong Dat', CAST(N'2020-02-04T15:28:22.000' AS DateTime), 36, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (19, N'.', N'Phuong Dat', CAST(N'2020-02-04T15:28:40.000' AS DateTime), 36, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (21, N'.', N'Phuong Dat', CAST(N'2020-02-04T15:32:34.000' AS DateTime), 36, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (30, N'hihi mot ngay dep troi', N'Hoa', CAST(N'2020-02-24T16:52:50.000' AS DateTime), 46, N'HoaDNT@gmail.com')
INSERT [dbo].[tbl_Comment] ([CommentID], [CommentContent], [Author], [TimeGenerated], [ArticleID], [Email]) VALUES (31, N'hihi ', N'Hoa', CAST(N'2020-02-24T16:53:04.000' AS DateTime), 46, N'HoaDNT@gmail.com')
SET IDENTITY_INSERT [dbo].[tbl_Comment] OFF
SET IDENTITY_INSERT [dbo].[tbl_Role] ON 

INSERT [dbo].[tbl_Role] ([RoleID], [RoleName]) VALUES (1, N'admin')
INSERT [dbo].[tbl_Role] ([RoleID], [RoleName]) VALUES (2, N'member')
SET IDENTITY_INSERT [dbo].[tbl_Role] OFF
SET IDENTITY_INSERT [dbo].[tbl_Status] ON 

INSERT [dbo].[tbl_Status] ([StatusID], [StatusName]) VALUES (1, N'new')
INSERT [dbo].[tbl_Status] ([StatusID], [StatusName]) VALUES (2, N'active')
INSERT [dbo].[tbl_Status] ([StatusID], [StatusName]) VALUES (3, N'delete')
SET IDENTITY_INSERT [dbo].[tbl_Status] OFF
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleID], [StatusID]) VALUES (N'admin@gmail.com', N'ADMIN', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 1, 2)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleID], [StatusID]) VALUES (N'HoaDNT@gmail.com', N'Hoa', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 2, 2)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleID], [StatusID]) VALUES (N'user_01@gmail.com', N'Meow', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 2, 1)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleID], [StatusID]) VALUES (N'user_02@gmail.com', N'Gau Gau', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 2, 2)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleID], [StatusID]) VALUES (N'user_03@gmail.com', N'Chip Chip', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 2, 3)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleID], [StatusID]) VALUES (N'vanthanhphuongdat1309@gmail.com', N'Phuong Dat', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 2, 2)
ALTER TABLE [dbo].[tbl_Article]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Article_tbl_Status] FOREIGN KEY([StatusID])
REFERENCES [dbo].[tbl_Status] ([StatusID])
GO
ALTER TABLE [dbo].[tbl_Article] CHECK CONSTRAINT [FK_tbl_Article_tbl_Status]
GO
ALTER TABLE [dbo].[tbl_Article]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Article_tbl_User] FOREIGN KEY([Email])
REFERENCES [dbo].[tbl_User] ([Email])
GO
ALTER TABLE [dbo].[tbl_Article] CHECK CONSTRAINT [FK_tbl_Article_tbl_User]
GO
ALTER TABLE [dbo].[tbl_Comment]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Comment_tbl_Article] FOREIGN KEY([ArticleID])
REFERENCES [dbo].[tbl_Article] ([ArticleID])
GO
ALTER TABLE [dbo].[tbl_Comment] CHECK CONSTRAINT [FK_tbl_Comment_tbl_Article]
GO
ALTER TABLE [dbo].[tbl_Comment]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Comment_tbl_User] FOREIGN KEY([Email])
REFERENCES [dbo].[tbl_User] ([Email])
GO
ALTER TABLE [dbo].[tbl_Comment] CHECK CONSTRAINT [FK_tbl_Comment_tbl_User]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[tbl_Role] ([RoleID])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Role]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Status] FOREIGN KEY([StatusID])
REFERENCES [dbo].[tbl_Status] ([StatusID])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Status]
GO
USE [master]
GO
ALTER DATABASE [SimpleBlog] SET  READ_WRITE 
GO
