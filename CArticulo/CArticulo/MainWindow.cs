using Gtk;
using System;
using System.Data;
using MySql.Data.MySqlClient;

using CArticulo;
using Serpis.Ad;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();
		App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

        TreeViewHelper.Fill(treeView, ArticuloDao.SelectAll);

        newAction.Activated += delegate {
            Articulo articulo = new Articulo();
            new ArticuloWindow(articulo);
        };

        editAction.Activated += delegate {
            object id = TreeViewHelper.GetId(treeView);
            Articulo articulo = ArticuloDao.Load(id);
            new ArticuloWindow(articulo);
        };

        refreshAction.Activated += delegate {
			TreeViewHelper.Fill(treeView, ArticuloDao.SelectAll);
		};
	}

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        App.Instance.Connection.Close();        
        Application.Quit();
        a.RetVal = true;
    }
}
