using System;

using Serpis.Ad;

namespace CArticulo
{
    public partial class ArticuloWindow : Gtk.Window
    {
        public ArticuloWindow(Articulo articulo) :
                base(Gtk.WindowType.Toplevel) {
            this.Build();

            entryNombre.Text = articulo.Nombre;
            spinButtonPrecio.Value = Convert.ToDouble(articulo.Precio);

            ComboBoxHelper.Fill(
                comboBoxCategoria, 
                "select id, nombre from categoria order by nombre", 
                articulo.Categoria
            );
            //entryCategoria.Text = articulo.Categoria.ToString();

            saveAction.Activated += delegate {
                articulo.Nombre = entryNombre.Text;
                articulo.Precio = Convert.ToDecimal(spinButtonPrecio.Value);
                //articulo.Categoria = long.Parse(entryCategoria.Text);
                ArticuloDao.Save(articulo);
                Destroy();
            };
        }
    }
}
